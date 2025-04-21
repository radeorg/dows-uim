package org.dows.uim.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.mybatisflex.core.keygen.IKeyGenerator;
import com.mybatisflex.core.keygen.KeyGeneratorFactory;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.aac.AacContext;
import org.dows.rade.constant.IdentifierType;
import org.dows.rade.encrypt.EncryptApi;
import org.dows.uim.constant.AccountType;
import org.dows.uim.entity.*;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.AddOrgAccountRequest;
import org.dows.uim.service.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class AccountHandler {

    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;
    private final AccountTypeService accountTypeService;

    private final OrgNodeService orgNodeService;
    private final OrgRegisterService orgRegisterService;
    private final OrgTreeService orgTreeService;

    private final EncryptApi encryptApi;

    private final AacContext aacContext;

    private final IKeyGenerator iKeyGenerator = KeyGeneratorFactory.getKeyGenerator(KeyGenerators.snowFlakeId);

    public Long addAccount(AccountInstanceRequest accountInstance) {
        // 保存账号 实例
        AccountInstanceEntity accountInstanceEntity =
                BeanUtil.copyProperties(accountInstance, AccountInstanceEntity.class);
        accountInstanceService.save(accountInstanceEntity);
        Long accountInstanceId = accountInstanceEntity.getAccountInstanceId();
        // 保存账号 标识
        AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
        accountIdentifierEntity.setAccountInstanceId(accountInstanceId);
        accountIdentifierEntity.setIdentifier(accountInstance.getIdentifier());
        // fix #2023-04-09 账号标识类型
        accountIdentifierEntity.setIdentifierType(accountInstance.getIdentifierType());
        accountIdentifierEntity.setAppId(accountInstance.getAppId());
        accountIdentifierService.save(accountIdentifierEntity);
        return accountInstanceId;
    }


    /*public Long addAccounts(AccountInstanceRequest accountInstance) {
        // 保存账号 实例
        AccountInstanceEntity accountInstanceEntity =
                BeanUtil.copyProperties(accountInstance, AccountInstanceEntity.class);
        accountInstanceService.save(accountInstanceEntity);
        Long accountInstanceId = accountInstanceEntity.getAccountInstanceId();
        // 保存账号 标识
        AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
        accountIdentifierEntity.setAccountInstanceId(accountInstanceId);
        accountIdentifierEntity.setIdentifier(accountInstance.getIdentifier());
        // fix #2023-04-09 账号标识类型
        accountIdentifierEntity.setType(accountInstance.getIdentifierType());
        accountIdentifierEntity.setAppId(accountInstance.getAppId());
        accountIdentifierService.save(accountIdentifierEntity);
        return accountInstanceId;
    }*/

    public void saveOrgAccount(AddOrgAccountRequest addOrgAccountRequest) {
        // 检测手机账号标识是否存在
        AccountIdentifierEntity one = accountIdentifierService.getOne(QueryWrapper.create()
                .eq(AccountIdentifierEntity::getIdentifier, addOrgAccountRequest.getTelephone())
                .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType()));

        Long accountInstanceId;
        AccountTypeEntity accountTypeEntity;
        if (one != null) {
            accountTypeEntity = AccountTypeEntity.builder()
                    .accountInstanceId(one.getAccountInstanceId())
                    .accountType(AccountType.JOB_HUNTER_ACCOUNT.getValue())
                    .build();
            accountInstanceId = one.getAccountInstanceId();
        } else {
            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            accountInstanceEntity.setNickname(addOrgAccountRequest.getAccountName());
            accountInstanceEntity.setPassword(encryptApi.getBCryptPassword(addOrgAccountRequest.getPassword()));
            accountInstanceEntity.setZoneNo(addOrgAccountRequest.getZoneNo());
            accountInstanceEntity.setTelephone(addOrgAccountRequest.getTelephone());
            /*accountInstanceEntity.setAvator("");
            accountInstanceEntity.setReferralsNo("");
            accountInstanceEntity.setSource("");
            accountInstanceEntity.setAppId("");
            accountInstanceEntity.setOperatorId(1L);*/
            accountInstanceEntity.setSuperAccount(0);
            accountInstanceService.save(accountInstanceEntity);
            accountInstanceId = accountInstanceEntity.getAccountInstanceId();
            AccountIdentifierEntity phoneIdentifier = AccountIdentifierEntity.builder()
                    .accountInstanceId(accountInstanceId)
                    .identifierType(IdentifierType.PHONE.getType())
                    .build();
            AccountIdentifierEntity emailIdentifier = AccountIdentifierEntity.builder()
                    .accountInstanceId(accountInstanceId)
                    .identifierType(IdentifierType.EMAIL.getType())
                    .build();
            List<AccountIdentifierEntity> identifiers = List.of(phoneIdentifier, emailIdentifier);
            // 批量保存账号标识
            accountIdentifierService.saveOrUpdateBatch(identifiers);
            // 账号类型
            accountTypeEntity = AccountTypeEntity.builder()
                    .accountInstanceId(accountInstanceEntity.getAccountInstanceId())
                    .accountType(AccountType.JOB_HUNTER_ACCOUNT.getValue())
                    .build();
        }
        // 保存账号 类型
        accountTypeService.save(accountTypeEntity);
        // 关联组织

        // todo 处理组织
        OrgTreeEntity orgNameExit = orgTreeService.getOne(QueryWrapper.create()
                .eq(OrgTreeEntity::getOrgName, addOrgAccountRequest.getOrgName())
                .eq(OrgTreeEntity::getAppId, addOrgAccountRequest.getAppId()));
        Long orgTreeId = addOrgAccountRequest.getOrgTreeId();
        Long orgRootId = aacContext.getAacUser().getOrgRootId();
        //OrgTreeEntity childOrgTreeEntity
        if (orgNameExit == null) {
            orgNameExit = new OrgTreeEntity();
            orgNameExit.setPid(Objects.nonNull(orgTreeId) ? orgTreeId : orgRootId);
            orgNameExit.setOrgName(addOrgAccountRequest.getOrgName());
            orgTreeService.save(orgNameExit);
        }
        OrgNodeEntity orgNodeEntity = new OrgNodeEntity();
        orgNodeEntity.setOrgRootId(orgRootId);
        orgNodeEntity.setOrgTreeId(orgNameExit.getOrgTreeId());
        orgNodeEntity.setAccountInstanceId(accountInstanceId);
        orgNodeService.save(orgNodeEntity);
    }

    public void saveOrgAccount(List<AddOrgAccountRequest> addOrgAccountRequests) {
        // 构建账号集合并批量保存账号实例
        // 构建标识集合并批量保存账号标识
        // 保存账号 类型

        List<String> telephones = addOrgAccountRequests.stream().map(AddOrgAccountRequest::getTelephone).toList();
        List<AccountIdentifierEntity> list = accountIdentifierService.list(QueryWrapper.create()
                .in(AccountIdentifierEntity::getIdentifier, telephones)
                .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType()));

        List<AddOrgAccountRequest> newAddOrgAccounts = new ArrayList<>();
        if (list != null) {
            Map<Long, String> collect = list.stream().collect(Collectors
                    .toMap(AccountIdentifierEntity::getAccountInstanceId, AccountIdentifierEntity::getIdentifier));
            List<AccountTypeEntity> accountTypeEntities = new ArrayList<>();
            for (Long accountInstanceId : collect.keySet()) {
                accountTypeEntities.add(AccountTypeEntity.builder()
                        .accountInstanceId(accountInstanceId)
                        .accountType(AccountType.JOB_HUNTER_ACCOUNT.getValue())
                        .build());
            }
            accountTypeService.saveOrUpdateBatch(accountTypeEntities);
            Collection<String> telephoneSet = collect.values();
            newAddOrgAccounts = addOrgAccountRequests.stream()
                    .filter(oa -> !telephoneSet.contains(oa.getTelephone())).toList();
        }

        List<AccountInstanceEntity> accountInstanceEntities = new ArrayList<>();
        newAddOrgAccounts.forEach(addOrgAccountRequest -> {
            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            accountInstanceEntity.setNickname(addOrgAccountRequest.getAccountName());
            accountInstanceEntity.setPassword(encryptApi.getBCryptPassword(addOrgAccountRequest.getPassword()));
            accountInstanceEntity.setZoneNo(addOrgAccountRequest.getZoneNo());
            accountInstanceEntity.setTelephone(addOrgAccountRequest.getTelephone());
            /*accountInstanceEntity.setAvator("");
            accountInstanceEntity.setReferralsNo("");
            accountInstanceEntity.setSource("");
            accountInstanceEntity.setAppId("");
            accountInstanceEntity.setOperatorId(1L);*/
            accountInstanceEntity.setSuperAccount(0);
            accountInstanceEntities.add(accountInstanceEntity);
        });
        // 批量保存账号实例
        accountInstanceService.saveOrUpdateBatch(accountInstanceEntities);
        List<AccountIdentifierEntity> accountIdentifierEntities = new ArrayList<>();
        List<AccountTypeEntity> accountTypeEntities = new ArrayList<>();
        accountInstanceEntities.forEach(accountInstanceEntity -> {
            // 保存账号 标识
            AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
            accountIdentifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
            accountIdentifierEntity.setIdentifier(accountInstanceEntity.getTelephone());
            accountIdentifierEntity.setIdentifierType(IdentifierType.PHONE.getType());
            // 保存账号类型
            AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
            accountTypeEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
            accountTypeEntity.setAccountType(AccountType.ORG_RECRUIT_ACCOUNT.getValue());
            accountIdentifierEntities.add(accountIdentifierEntity);
            accountTypeEntities.add(accountTypeEntity);
        });
        // 批量保存账号标识
        accountIdentifierService.saveOrUpdateBatch(accountIdentifierEntities);
        // 批量保存账号类型
        accountTypeService.saveOrUpdateBatch(accountTypeEntities);

        // 保存组织信息
        // 保存组织成员信息
        List<String> orgNames = addOrgAccountRequests.stream().map(AddOrgAccountRequest::getOrgName).toList();
        // todo 处理组织
        List<OrgTreeEntity> orgNameExits = orgTreeService.list(QueryWrapper.create()
                .in(OrgTreeEntity::getOrgName, orgNames)
                .eq(OrgTreeEntity::getAppId, addOrgAccountRequests.get(0).getAppId()));
        if (CollectionUtil.isNotEmpty(orgNameExits)) {
            Set<String> collect = orgNameExits.stream().map(OrgTreeEntity::getOrgName).collect(Collectors.toSet());
            List<AddOrgAccountRequest> addOrgAccountRequestList = addOrgAccountRequests.stream()
                    .filter(on -> !collect.contains(on.getOrgName()))
                    .toList();


           /* OrgRegisterEntity superAccount = orgRegisterService.getOne(QueryWrapper.create()
                    .eq(OrgRegisterEntity::getAccountInstanceId, ""));*/

            Long orgRootId = aacContext.getAacUser().getOrgRootId();
            List<OrgTreeEntity> orgTreeEntities = new ArrayList<>();
            List<OrgNodeEntity> orgNodeEntities = new ArrayList<>();
            for (int i = 0; i < addOrgAccountRequestList.size(); i++) {
                OrgTreeEntity childOrgTreeEntity = new OrgTreeEntity();
                Long orgTreeId = addOrgAccountRequestList.get(i).getOrgTreeId();
                childOrgTreeEntity.setPid(Objects.nonNull(orgTreeId) ? orgTreeId : orgRootId);
                Long newOrgTreeId = Long.valueOf(iKeyGenerator.generate(null, null).toString());
                childOrgTreeEntity.setOrgTreeId(newOrgTreeId);
                childOrgTreeEntity.setOrgName(addOrgAccountRequestList.get(i).getOrgName());
                orgTreeEntities.add(childOrgTreeEntity);

                OrgNodeEntity orgNodeEntity = new OrgNodeEntity();
                orgNodeEntity.setOrgRootId(orgRootId);
                orgNodeEntity.setOrgTreeId(childOrgTreeEntity.getOrgTreeId());
                orgNodeEntity.setAccountInstanceId(accountInstanceEntities.get(i).getAccountInstanceId());
                orgNodeEntities.add(orgNodeEntity);
            }
            //orgTreeService
            orgTreeService.saveBatch(orgTreeEntities);
            orgNodeService.saveBatch(orgNodeEntities);
        }

    }
}
