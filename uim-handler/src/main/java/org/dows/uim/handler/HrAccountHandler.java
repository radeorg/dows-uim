package org.dows.uim.handler;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.constant.IdentifierType;
import org.dows.rade.status.CommonStatusCode;
import org.dows.uim.constant.AccountType;
import org.dows.uim.constant.CommonDelEnum;
import org.dows.uim.entity.*;
import org.dows.uim.exception.UimException;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.AddOrgAccountRequest;
import org.dows.uim.request.HrAccountInstanceRequest;
import org.dows.uim.response.HrAccountInstanceResponse;
import org.dows.uim.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class HrAccountHandler {

    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;
    private final AccountTypeService accountTypeService;

    private final OrgNodeService orgNodeService;
    private final OrgRegisterService orgRegisterService;
    private final OrgTreeService orgTreeService;

    public Page<HrAccountInstanceResponse> page(HrAccountInstanceRequest request) {

        // 创建分页对象
        Page<HrAccountInstanceResponse> page = new Page<>(
                Long.valueOf(request.getPageNum()),
                Long.valueOf(request.getPageSize())
        );
        Page<HrAccountInstanceResponse> resultPage = QueryChain.of(AccountInstanceEntity.class)
                .select(AccountInstanceEntity::getAccountInstanceId,AccountInstanceEntity::getNickname)
//                .innerJoin(AccountIdentifierEntity.class)
//                .on(AccountInstanceEntity::getAccountInstanceId, AccountIdentifierEntity::getAccountInstanceId)
                .innerJoin(AccountTypeEntity.class)
                .on(AccountInstanceEntity::getAccountInstanceId, AccountTypeEntity::getAccountInstanceId)
                .innerJoin(OrgNodeEntity.class)
                .on(OrgNodeEntity::getAccountInstanceId, AccountInstanceEntity::getAccountInstanceId)
                .innerJoin(OrgTreeEntity.class)
                .on(OrgTreeEntity::getId, OrgNodeEntity::getOrgTreeId)
                .select(OrgTreeEntity::getOrgName)
                .eq(AccountTypeEntity::getAccountType,AccountType.ORG_RECRUIT_ACCOUNT.getValue())
                .eq(AccountInstanceEntity::getAppId,request.getAppId(), Objects.nonNull(request.getAppId()))
                .eq(OrgNodeEntity::getOrgRootId,request.getOrgRootId(), Objects.nonNull(request.getOrgRootId()))
                .like(AccountInstanceEntity::getNickname,request.getNickname(), Objects.nonNull(request.getNickname()))
                .like(AccountInstanceEntity::getTelephone,request.getTelephone(), Objects.nonNull(request.getTelephone()))
                .like(OrgTreeEntity::getOrgName,request.getOrgName(), Objects.nonNull(request.getOrgName()))
                .eq(AccountInstanceEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .orderBy(AccountInstanceEntity::getTs, false)
                .pageAs(page,HrAccountInstanceResponse.class);

        if(resultPage == null || resultPage.getRecords().isEmpty()){
            return resultPage;
        }
        resultPage.getRecords().stream().forEach(hrAccountInstanceResponse -> {
            hrAccountInstanceResponse.setResumeCount(1L);
            hrAccountInstanceResponse.setInterviewCount(1L);
        });
        return resultPage;
    }
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


    public void saveOrgAccount(List<AddOrgAccountRequest> addOrgAccountRequests) {
        // 构建账号集合并批量保存账号实例
        // 构建标识集合并批量保存账号标识
        // 保存账号 类型
        List<AccountInstanceEntity> accountInstanceEntities = new ArrayList<>();
        addOrgAccountRequests.forEach(addOrgAccountRequest -> {
            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            //accountInstanceEntity.setIdentifier(addOrgAccountRequest.getAccountName());
            accountInstanceEntity.setPassword(addOrgAccountRequest.getPassword());
            accountInstanceEntity.setZoneNo(addOrgAccountRequest.getZoneNo());
            accountInstanceEntity.setTelephone(addOrgAccountRequest.getPhone());
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
        OrgRegisterEntity superAccount = orgRegisterService.getOne(QueryWrapper.create()
                .eq(OrgRegisterEntity::getAccountInstanceId, ""));
        
        Long orgRootId = superAccount.getOrgRootId();
        List<OrgTreeEntity> orgTreeEntities = new ArrayList<>();
        List<OrgNodeEntity> orgNodeEntities = new ArrayList<>();
        for (int i = 0; i < addOrgAccountRequests.size(); i++) {
            OrgTreeEntity childOrgTreeEntity = new OrgTreeEntity();
            childOrgTreeEntity.setPid(orgRootId);
            childOrgTreeEntity.setOrgName(addOrgAccountRequests.get(i).getOrgName());
            orgTreeEntities.add(childOrgTreeEntity);

            OrgNodeEntity orgNodeEntity = new OrgNodeEntity();
            orgNodeEntity.setOrgRootId(orgRootId);
            orgNodeEntity.setOrgTreeId(childOrgTreeEntity.getOrgTreeId());
            orgNodeEntity.setAccountInstanceId(accountInstanceEntities.get(i).getAccountInstanceId());
            orgNodeEntities.add(orgNodeEntity);
        }
//        orgTreeService
        orgTreeService.saveBatch(orgTreeEntities);
        orgNodeService.saveBatch(orgNodeEntities);

    }

    /**
     * 删除
     * 删除面试官
     * @param accountInstanceId
     * @return
     */
    @Transactional
    public Boolean delete(Long accountInstanceId) {
        AccountInstanceEntity accountInstanceEntity = accountInstanceService.getById(accountInstanceId);
        if (accountInstanceEntity == null || Objects.equals(CommonDelEnum.DELETE.getCode(), accountInstanceEntity.getDeleted())) {
            log.warn("招聘官删除失败,未找到有效的招聘官：{}", accountInstanceId);
            return false;
        }

        boolean update = UpdateChain.of(AccountInstanceEntity.class)
                .set(AccountInstanceEntity::getDeleted, CommonDelEnum.DELETE.getCode())
                .set(AccountInstanceEntity::getUt, new Date())
                .set(AccountInstanceEntity::getVer, accountInstanceEntity.getVer() + 1)
                .eq(AccountInstanceEntity::getAccountInstanceId, accountInstanceId, Objects.nonNull(accountInstanceId))
                .eq(AccountInstanceEntity::getVer, accountInstanceEntity.getVer())
                .update();
        if (!update) {
            log.warn("招聘官删除失败,操作失败请重试：{}", accountInstanceId);
            throw new UimException(CommonStatusCode.FAILED);
        }
        return true;
    }
}
