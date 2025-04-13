package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.constant.IdentifierType;
import org.dows.uim.api.AccountTypeRequest;
import org.dows.uim.api.AccountTypeResponse;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.entity.AccountRoleEntity;
import org.dows.uim.entity.AccountTypeEntity;
import org.dows.uim.handler.AccountHandler;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.AddOrgAccountRequest;
import org.dows.uim.request.BindingAccountRequest;
import org.dows.uim.request.FindAccountIdentifierRequest;
import org.dows.uim.response.*;
import org.dows.uim.service.AccountIdentifierService;
import org.dows.uim.service.AccountInstanceService;
import org.dows.uim.service.AccountTypeService;
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
    private final AccountTypeService accountTypeService;


    private final AccountHandler accountHandler;

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
        accountIdentifierEntity.setIdentifierType(accountInstance.getIdentifierType());
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
        response.setIdentifier(accountInstanceEntity.getTelephone());
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
        AccountInstanceResponse accountInstanceResponse = BeanUtil.copyProperties(accountInstanceEntity, AccountInstanceResponse.class);
        accountInstanceResponse.setIdentifier(accountIdentifier);
        return  accountInstanceResponse;
    }

    public AccountOrgIdsResponse getOrgIdsByAccountId(String appId, Long accountInstanceId, boolean check) {
        AccountOrgIdsResponse response = new AccountOrgIdsResponse();

        return response;
    }

    public List<AccountRoleRelationResponse> getRoleByAccountInstanceId(String appId,  List<Long> principals) {
        List<AccountRoleRelationResponse> response = new ArrayList<>();

        return List.of();
    }

    public List<AddOrgAccountResponse> saveOrgAccount(List<AddOrgAccountRequest> addOrgAccountRequests) {


        accountHandler.saveOrgAccount(addOrgAccountRequests);
        return null;
    }

    /**
     * 获取账号类型
     *
     * @param accountTypeRequest
     * @return
     */
    public List<AccountTypeResponse> getAccountType(AccountTypeRequest accountTypeRequest) {
        List<AccountTypeEntity> list = accountTypeService.list(QueryWrapper.create()
                .eq(AccountTypeEntity::getAccountInstanceId, accountTypeRequest.getAccountInstanceId()));
        return BeanUtil.copyToList(list, AccountTypeResponse.class);
    }

    public void bindingAccount(BindingAccountRequest bindingAccountRequest) {

        Long accountInstanceId = bindingAccountRequest.getAccountInstanceId();
        AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
        accountInstanceEntity.setNickname(bindingAccountRequest.getNickname());
        accountInstanceEntity.setAvatar(bindingAccountRequest.getAvatar());
        accountInstanceEntity.setTelephone(bindingAccountRequest.getTelephone());
        accountInstanceEntity.setZoneNo(bindingAccountRequest.getZoneNo());
        // 构建查询条件
        QueryWrapper eq = QueryWrapper.create()
                .eq(AccountInstanceEntity::getAccountInstanceId, accountInstanceId);
        // 根据查询条件accountId&appId更新账号信息到账号实例
        accountInstanceService.update(accountInstanceEntity, eq);
        // 查询账号标识（手机号）是否存在
        QueryWrapper eq1 = QueryWrapper.create()
                .eq(AccountIdentifierEntity::getAccountInstanceId, accountInstanceId)
                .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType())
                .eq(AccountIdentifierEntity::getIdentifier, bindingAccountRequest.getTelephone());
        AccountIdentifierEntity one = accountIdentifierService.getOne(eq1);
        if (one == null) {
            // 保存账号标识
            accountIdentifierService.save(AccountIdentifierEntity.builder()
                    .accountInstanceId(accountInstanceId)
                    .identifier(bindingAccountRequest.getTelephone())
                    .identifierType(IdentifierType.PHONE.getType())
                    .build());
        }
    }
}
