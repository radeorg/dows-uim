package org.dows.uim.handler;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.constant.IdentifierType;
import org.dows.uim.constant.AccountType;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.entity.AccountTypeEntity;
import org.dows.uim.entity.OrgRegisterEntity;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.AddOrgAccountRequest;
import org.dows.uim.service.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class AccountHandler {

    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;
    private final AccountTypeService accountTypeService;

    private final OrgNodeService orgNodeService;
    private final OrgRegisterService orgRegisterService;

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
        accountIdentifierEntity.setType(accountInstance.getIdentifierType());
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

    public void saveOrgAccount(List<AddOrgAccountRequest> addOrgAccountRequests) {
        // 构建账号集合并批量保存账号实例
        // 构建标识集合并批量保存账号标识
        // 保存账号 类型
        List<AccountInstanceEntity> accountInstanceEntities = new ArrayList<>();
        addOrgAccountRequests.forEach(addOrgAccountRequest -> {
            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            accountInstanceEntity.setIdentifier(addOrgAccountRequest.getAccountName());
            accountInstanceEntity.setPassword(addOrgAccountRequest.getPassword());
            accountInstanceEntity.setZoneNo(addOrgAccountRequest.getZoneNo());
            accountInstanceEntity.setCellphone(addOrgAccountRequest.getPhone());
            /*accountInstanceEntity.setAvator("");
            accountInstanceEntity.setReferralsNo("");
            accountInstanceEntity.setSource("");
            accountInstanceEntity.setAppId("");
            accountInstanceEntity.setOperatorId(1L);*/
            accountInstanceEntity.setSuperAccount(true);
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
            accountIdentifierEntity.setIdentifier(accountInstanceEntity.getCellphone());
            accountIdentifierEntity.setType(IdentifierType.PHONE.getType());
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


    }
}
