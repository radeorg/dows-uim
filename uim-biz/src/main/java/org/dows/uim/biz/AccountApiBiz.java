package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.FindAccountIdentifierRequest;
import org.dows.uim.response.AccountIdentifierResponse;
import org.dows.uim.response.AccountInstanceResponse;
import org.dows.uim.response.AccountOrgIdsResponse;
import org.dows.uim.response.AccountRoleRelationResponse;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.entity.AccountRoleEntity;
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

    /**
     *
     * @param accountInstance
     * @return
     */
    public Long accountRegister(AccountInstanceRequest accountInstance) {
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

    public AccountInstanceResponse getAccountInstanceByAccountName(String appId, String accountName) {
        AccountInstanceResponse response = new AccountInstanceResponse();

        List<AccountIdentifierEntity> accountIdentifierEntityList = QueryChain.of(AccountIdentifierEntity.class)
                .eq(AccountIdentifierEntity::getIdentifier, accountName, Objects.nonNull(accountName))
                .eq(AccountIdentifierEntity::getAppId, appId, Objects.nonNull(appId)).list();
        if(Objects.isNull(accountIdentifierEntityList) || accountIdentifierEntityList.isEmpty()){
            return response;
        }

        Long accountInstanceId = accountIdentifierEntityList.get(0).getAccountInstanceId();

        AccountInstanceEntity accountInstanceEntity = QueryChain.of(AccountInstanceEntity.class)
                .eq(AccountInstanceEntity::getAccountInstanceId, accountInstanceId, Objects.nonNull(accountInstanceId))
                .eq(AccountInstanceEntity::getAppId, appId, Objects.nonNull(appId)).one();
        if(Objects.isNull(accountInstanceEntity)){
            return response;
        }

        BeanUtil.copyProperties(accountInstanceEntity, response);
        response.setIdentifier(accountInstanceEntity.getIdentifier());
        response.setSuperAccount(true);

        return response;
    }

    public AccountIdentifierResponse getAccountIdentifier(String appId, FindAccountIdentifierRequest findAccountIdentifierRequest) {
        AccountIdentifierResponse response = new AccountIdentifierResponse();
        return response;
    }

    /**
     *
     * @param appId
     * @param accountInstanceId
     * @return
     */
    public List<Long> getAllRoleIds(String appId, Long accountInstanceId){
        List<Long> roleList = new ArrayList<>();

        List<AccountRoleEntity> accountRoleEntityList = QueryChain.of(AccountRoleEntity.class)
                .eq(AccountRoleEntity::getAppId, appId, Objects.nonNull(appId))
                .eq(AccountRoleEntity::getAccountInstanceId, accountInstanceId, Objects.nonNull(accountInstanceId)).list();
        if(Objects.isNull(accountRoleEntityList) || accountRoleEntityList.isEmpty()){
            return roleList;
        }
        for(AccountRoleEntity item : accountRoleEntityList){
            roleList.add(item.getRbacRoleId());
        }

        return roleList;
    }
    /**
     *
     * @param appId
     * @param accountIdentifier
     * @return
     */
    public AccountInstanceResponse getAccountInstanceByIdentifier(String appId, String accountIdentifier) {

        List<AccountIdentifierEntity> accountIdentifierEntityList = QueryChain.of(AccountIdentifierEntity.class)
                .eq(AccountIdentifierEntity::getIdentifier, accountIdentifier, Objects.nonNull(accountIdentifier))
                .eq(AccountIdentifierEntity::getAppId, appId, Objects.nonNull(appId)).list();
        if(Objects.isNull(accountIdentifierEntityList) || accountIdentifierEntityList.isEmpty()){
            return null;
        }

        Long accountInstanceId = accountIdentifierEntityList.get(0).getAccountInstanceId();
        AccountInstanceEntity accountInstanceEntity = QueryChain.of(AccountInstanceEntity.class)
                .eq(AccountInstanceEntity::getAccountInstanceId, accountInstanceId, Objects.nonNull(accountInstanceId))
                .eq(AccountInstanceEntity::getAppId, appId, Objects.nonNull(appId)).one();
        if(Objects.isNull(accountInstanceEntity)){
            return null;
        }
        return  BeanUtil.copyProperties(accountInstanceEntity, AccountInstanceResponse.class);
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
