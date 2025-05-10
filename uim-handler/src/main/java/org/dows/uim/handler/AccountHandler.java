package org.dows.uim.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
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
import org.dows.uim.request.SaveOrgAccountRequest;
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

    public void saveOrgAccount(SaveOrgAccountRequest saveOrgAccountRequest){

        AccountInstanceEntity accountInstanceEntity = null;
        if (saveOrgAccountRequest.getAccountInstanceId() != null) {
            accountInstanceEntity = accountInstanceService.getById(saveOrgAccountRequest.getAccountInstanceId());
        }
        // 修改，修改逻辑包括昵称，密码，组织关系等
        if (accountInstanceEntity != null) {
            // 更新昵称
            accountInstanceEntity.setNickname(saveOrgAccountRequest.getNickname());
            // 更新密码
            String password = saveOrgAccountRequest.getPassword();
            if (!StrUtil.isBlank(password)) {
                accountInstanceEntity.setPassword(encryptApi.getBCryptPassword(password));
            }
            // 更新组织关系
            // 检查是否已经存在关联的组织
            OrgNodeEntity dbOrgNode = orgNodeService.getOne(QueryWrapper.create()
                    .eq(OrgNodeEntity::getAccountInstanceId, accountInstanceEntity.getAccountInstanceId())
                    .eq(OrgNodeEntity::getOrgTreeId, saveOrgAccountRequest.getOrgTreeId(), Objects.nonNull(saveOrgAccountRequest.getOrgTreeId()))
                    .eq(OrgNodeEntity::getOrgRootId, aacContext.getAacUser().getOrgRootId(), Objects.nonNull(aacContext.getAacUser().getOrgRootId())));
            if (dbOrgNode == null) {
                // 如果不存在，则创建新的组织关联
                OrgNodeEntity orgNodeEntity = OrgNodeEntity.builder()
                        .orgTreeId(saveOrgAccountRequest.getOrgTreeId())
                        .orgRootId(aacContext.getAacUser().getOrgRootId())
                        .accountInstanceId(accountInstanceEntity.getAccountInstanceId())
                        .build();
                orgNodeService.save(orgNodeEntity);
            } else {
                // 如果存在，则更新现有的组织关联
                if(Objects.nonNull(dbOrgNode.getOrgTreeId())) {
                    dbOrgNode.setOrgTreeId(dbOrgNode.getOrgTreeId());
                }else{
                    dbOrgNode.setOrgTreeId(saveOrgAccountRequest.getOrgTreeId());
                }
                dbOrgNode.setAliasName(saveOrgAccountRequest.getOrgName());
                dbOrgNode.setUt(new Date());
                orgNodeService.updateById(dbOrgNode);
            }
            // 保存更新后的账号实例
            accountInstanceService.updateById(accountInstanceEntity,true);

            if(Objects.nonNull(dbOrgNode.getOrgTreeId())){
                OrgTreeEntity dbOrgTree = orgTreeService.getOne(QueryWrapper.create()
                        .eq(OrgTreeEntity::getOrgTreeId, dbOrgNode.getOrgTreeId()));
                dbOrgTree.setOrgName(saveOrgAccountRequest.getOrgName());
                dbOrgTree.setUt(new Date());
                orgTreeService.saveOrUpdate(dbOrgTree);
            }

        } else { // 新增
            // 检测手机账号标识是否存在
            AccountIdentifierEntity one = accountIdentifierService.getOne(QueryWrapper.create()
                    .eq(AccountIdentifierEntity::getIdentifier, saveOrgAccountRequest.getTelephone())
                    .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType()));

            Long accountInstanceId;
            AccountTypeEntity accountTypeEntity;

            String password = saveOrgAccountRequest.getPassword();
            String bCryptPassword = "";
            if(Objects.nonNull(password)) {
                bCryptPassword = encryptApi.getBCryptPassword(password);
            }
            if (one != null) {
                //判断在组织树是否存在，如存在提示重复
                // 检查是否已经存在关联的组织
                OrgNodeEntity dbOrgNode = orgNodeService.getOne(QueryWrapper.create()
                        .eq(OrgNodeEntity::getAccountInstanceId, one.getAccountInstanceId())
                        .eq(OrgNodeEntity::getOrgRootId, aacContext.getAacUser().getOrgRootId(), Objects.nonNull(aacContext.getAacUser().getOrgRootId())));
                if (Objects.nonNull(dbOrgNode)) {
                    throw new RuntimeException("该手机号已经存在，不能重复提交");
                }

                accountTypeEntity = AccountTypeEntity.builder()
                        .accountInstanceId(one.getAccountInstanceId())
                        .accountType(saveOrgAccountRequest.getAccountType().getValue())
                        .build();
                accountInstanceId = one.getAccountInstanceId();
                // todo 如果用户在小程序端已经注册账号，则直接更新账号类型，同时也更新账号信息，此处可以更新密码，使账号可以密码方式登录
                accountInstanceEntity = new AccountInstanceEntity();
                accountInstanceEntity.setAccountInstanceId(accountInstanceId);
                accountInstanceEntity.setPassword(bCryptPassword);
                // todo 如果变更手机号，需要重写一个接口
//            accountInstanceEntity.setTelephone();
                accountInstanceService.updateById(accountInstanceEntity);
            } else {
                accountInstanceEntity = new AccountInstanceEntity();
                accountInstanceEntity.setNickname(saveOrgAccountRequest.getNickname());
                accountInstanceEntity.setPassword(bCryptPassword);
                accountInstanceEntity.setZoneNo(saveOrgAccountRequest.getZoneNo());
                accountInstanceEntity.setTelephone(saveOrgAccountRequest.getTelephone());
            /*accountInstanceEntity.setAvator("");
            accountInstanceEntity.setReferralsNo("");
            accountInstanceEntity.setSource("");
            accountInstanceEntity.setAppId("");
            accountInstanceEntity.setOperatorId(1L);*/
                // 设置为超级账号
                accountInstanceEntity.setSuperAccount(0);
                accountInstanceService.save(accountInstanceEntity);
                accountInstanceId = accountInstanceEntity.getAccountInstanceId();
                AccountIdentifierEntity phoneIdentifier = AccountIdentifierEntity.builder()
                        .accountInstanceId(accountInstanceId)
                        .identifierType(IdentifierType.PHONE.getType())
                        .identifier(saveOrgAccountRequest.getTelephone())
                        .build();

                List<AccountIdentifierEntity> identifiers = List.of(phoneIdentifier);
                if (!StrUtil.isBlank(saveOrgAccountRequest.getEmail())) {
                    AccountIdentifierEntity emailIdentifier = AccountIdentifierEntity.builder()
                            .accountInstanceId(accountInstanceId)
                            .identifierType(IdentifierType.EMAIL.getType())
                            .identifier(saveOrgAccountRequest.getEmail())
                            .build();
                    identifiers.add(emailIdentifier);
                }
                // 批量保存账号标识
                accountIdentifierService.saveBatch(identifiers);
                // 账号类型
                accountTypeEntity = AccountTypeEntity.builder()
                        .accountInstanceId(accountInstanceEntity.getAccountInstanceId())
                        .accountType(saveOrgAccountRequest.getAccountType().getValue())
                        .build();
            }
            AccountTypeEntity dbAccountType = accountTypeService.getOne(QueryWrapper.create()
                    .eq(AccountTypeEntity::getAccountInstanceId, accountInstanceId)
                    .eq(AccountTypeEntity::getAccountType, saveOrgAccountRequest.getAccountType().getValue()));
            // 如果为空时，保存账号 类型
            if (dbAccountType == null) {
                accountTypeService.save(accountTypeEntity);
            }
            // 关联组织
            // todo 处理组织
            OrgTreeEntity dbOrgTree = orgTreeService.getOne(QueryWrapper.create()
                    .eq(OrgTreeEntity::getOrgName, saveOrgAccountRequest.getOrgName())
                    .eq(OrgTreeEntity::getAppId, saveOrgAccountRequest.getAppId()));
            Long orgTreeId = saveOrgAccountRequest.getOrgTreeId();
            if(Objects.isNull(orgTreeId)){
                orgTreeId = dbOrgTree.getOrgTreeId();
            }
            Long orgRootId = aacContext.getAacUser().getOrgRootId();


            //OrgTreeEntity childOrgTreeEntity
            if (dbOrgTree == null) {
                dbOrgTree = new OrgTreeEntity();
                dbOrgTree.setPid(Objects.nonNull(orgTreeId) ? orgTreeId : orgRootId);
                dbOrgTree.setOrgName(saveOrgAccountRequest.getOrgName());
                orgTreeService.save(dbOrgTree);
            }

            // 如果已经绑定，不再绑定
            OrgNodeEntity dbOrgNode = orgNodeService.getOne(QueryWrapper.create()
                    .eq(OrgNodeEntity::getAccountInstanceId, accountInstanceId)
                    .eq(OrgNodeEntity::getOrgTreeId, orgTreeId)
                    .eq(OrgNodeEntity::getOrgRootId, orgRootId));
            if (dbOrgNode == null) {
                OrgNodeEntity orgNodeEntity = OrgNodeEntity.builder()
                        .orgTreeId(dbOrgTree.getOrgTreeId())
                        .orgRootId(orgRootId)
                        .accountInstanceId(accountInstanceId)
                        .build();
                orgNodeEntity.setAliasName(saveOrgAccountRequest.getOrgName());
                orgNodeService.save(orgNodeEntity);
            }
        }
    }

    public void saveOrgAccount(List<SaveOrgAccountRequest> saveOrgAccountRequests) {
        // 构建账号集合并批量保存账号实例
        // 构建标识集合并批量保存账号标识
        // 保存账号 类型

        List<String> telephones = saveOrgAccountRequests.stream().map(SaveOrgAccountRequest::getTelephone).toList();
        List<AccountIdentifierEntity> list = accountIdentifierService.list(QueryWrapper.create()
                .in(AccountIdentifierEntity::getIdentifier, telephones)
                .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType()));

        List<SaveOrgAccountRequest> newAddOrgAccounts = new ArrayList<>();
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
            newAddOrgAccounts = saveOrgAccountRequests.stream()
                    .filter(oa -> !telephoneSet.contains(oa.getTelephone())).toList();
        }

        List<AccountInstanceEntity> accountInstanceEntities = new ArrayList<>();
        newAddOrgAccounts.forEach(saveOrgAccountRequest -> {
            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            accountInstanceEntity.setNickname(saveOrgAccountRequest.getNickname());
            accountInstanceEntity.setPassword(encryptApi.getBCryptPassword(saveOrgAccountRequest.getPassword()));
            accountInstanceEntity.setZoneNo(saveOrgAccountRequest.getZoneNo());
            accountInstanceEntity.setTelephone(saveOrgAccountRequest.getTelephone());
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
        List<String> orgNames = saveOrgAccountRequests.stream().map(SaveOrgAccountRequest::getOrgName).toList();
        // todo 处理组织
        List<OrgTreeEntity> orgNameExits = orgTreeService.list(QueryWrapper.create()
                .in(OrgTreeEntity::getOrgName, orgNames)
                .eq(OrgTreeEntity::getAppId, saveOrgAccountRequests.get(0).getAppId()));
        if (CollectionUtil.isNotEmpty(orgNameExits)) {
            Set<String> collect = orgNameExits.stream().map(OrgTreeEntity::getOrgName).collect(Collectors.toSet());
            List<SaveOrgAccountRequest> saveOrgAccountRequestList = saveOrgAccountRequests.stream()
                    .filter(on -> !collect.contains(on.getOrgName()))
                    .toList();


           /* OrgRegisterEntity superAccount = orgRegisterService.getOne(QueryWrapper.create()
                    .eq(OrgRegisterEntity::getAccountInstanceId, ""));*/

            Long orgRootId = aacContext.getAacUser().getOrgRootId();
            List<OrgTreeEntity> orgTreeEntities = new ArrayList<>();
            List<OrgNodeEntity> orgNodeEntities = new ArrayList<>();
            for (int i = 0; i < saveOrgAccountRequestList.size(); i++) {
                OrgTreeEntity childOrgTreeEntity = new OrgTreeEntity();
                Long orgTreeId = saveOrgAccountRequestList.get(i).getOrgTreeId();
                childOrgTreeEntity.setPid(Objects.nonNull(orgTreeId) ? orgTreeId : orgRootId);
                Long newOrgTreeId = Long.valueOf(iKeyGenerator.generate(null, null).toString());
                childOrgTreeEntity.setOrgTreeId(newOrgTreeId);
                childOrgTreeEntity.setOrgName(saveOrgAccountRequestList.get(i).getOrgName());
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
