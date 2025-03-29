package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.api.request.AccountInstanceRequest;
import org.dows.uim.api.request.FindAccountIdentifierRequest;
import org.dows.uim.api.response.AccountIdentifierResponse;
import org.dows.uim.api.response.AccountInstanceResponse;
import org.dows.uim.api.response.AccountOrgIdsResponse;
import org.dows.uim.api.response.AccountRoleRelationResponse;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.service.AccountIdentifierService;
import org.dows.uim.service.AccountInstanceService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountApiBiz {
    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;

    public Long setAccountInstance(AccountInstanceRequest accountInstance) {
        // 保存账号 实例
        AccountInstanceEntity accountInstanceEntity =
                BeanUtil.copyProperties(accountInstance, AccountInstanceEntity.class);
        accountInstanceService.save(accountInstanceEntity);
        Long accountInstanceId = accountInstanceEntity.getAccountInstanceId();
        // 保存账号 标识
        AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
        accountIdentifierEntity.setAccountInstanceId(accountInstanceId);
        accountIdentifierEntity.setIdentifier(accountInstance.getIdentifier());
        accountIdentifierService.save(accountIdentifierEntity);
        return accountInstanceId;
    }

    public AccountInstanceResponse getAccountInstanceByAccountName(String appId, String accountName) {
        AccountInstanceResponse response = new AccountInstanceResponse();
        List<AccountInstanceEntity> accountInstanceEntityList = QueryChain.of(AccountInstanceEntity.class)
                .eq(AccountInstanceEntity::getIdentifier, accountName, Objects.nonNull(accountName))
                .eq(AccountInstanceEntity::getAppId, appId, Objects.nonNull(appId)).list();
        if(Objects.isNull(accountInstanceEntityList) || accountInstanceEntityList.size() == 0){
            return response;
        }
        AccountInstanceEntity entity = new AccountInstanceEntity();
        response.setAccountInstanceId(accountInstanceEntityList.get(0).getAccountInstanceId());
        response.setAccountName(accountInstanceEntityList.get(0).getIdentifier());
        response.setPassword(accountInstanceEntityList.get(0).getPassword());
        response.setSuperAccount(true);

        return response;
    }

    public AccountIdentifierResponse getAccountIdentifier(String appId, FindAccountIdentifierRequest findAccountIdentifierRequest) {
        AccountIdentifierResponse response = new AccountIdentifierResponse();
        return response;
    }

    public AccountInstanceResponse getAccountInstanceById(String appId, Long accountIdentifier) {
        AccountInstanceResponse response = new AccountInstanceResponse();

        return response;
    }

    public AccountOrgIdsResponse getOrgIdsByAccountId(String appId, Long accountInstanceId, boolean check) {
        AccountOrgIdsResponse response = new AccountOrgIdsResponse();

        return response;
    }

    public List<AccountRoleRelationResponse> getRoleByAccountInstanceId(String appId,  List<Long> principals) {
        List<AccountRoleRelationResponse> response = new ArrayList<>();

        return List.of();
    }
}
