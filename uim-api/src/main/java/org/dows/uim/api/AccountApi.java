package org.dows.uim.api;

import org.dows.uim.api.request.AccountInstanceRequest;
import org.dows.uim.api.request.FindAccountIdentifierRequest;
import org.dows.uim.api.response.AccountIdentifierResponse;
import org.dows.uim.api.response.AccountInstanceResponse;
import org.dows.uim.api.response.AccountOrgIdsResponse;
import org.dows.uim.api.response.AccountRoleRelationResponse;

import java.util.List;

public interface AccountApi {

    /**
     * 注册即创建
     *
     * @param appId
     * @param accountInstanceRequest
     * @return
     */
    default Long getAccountWithRegister(String appId, AccountInstanceRequest accountInstanceRequest) {
        throw new UnsupportedOperationException("not class implement");
    }


    /**
     * 根据账号标识获取账号实例ID
     *
     * @param appId
     * @param identifier
     * @return
     */
    default AccountInstanceResponse getAccountInstanceId(String appId, String identifier) {
        throw new UnsupportedOperationException("not class implement");
    }

    /**
     * 获取账号的角色ID集合
     *
     * @param appId
     * @param accountId
     * @return
     */
    default List<Long> getRoleIdsByAccountId(String appId, Long accountId) {
        throw new UnsupportedOperationException("not class implement");
    }

    /**
     * 获取账号所有关联的角色ID(个人和所在组织所拥有的角色)
     *
     * @param appId
     * @return
     */
    default List<Long> getAllRoleIds(String appId, Long accountId) {
        throw new UnsupportedOperationException("not class implement");
    }

    /**
     * 获取账号的组织ID集合
     *
     * @param appId
     * @param accountId
     * @return
     */
    default List<Long> getOrgIdsByAccountId(String appId, Long accountId, boolean check) {
        throw new UnsupportedOperationException("not class implement");
    }





    Long setAccountInstance(AccountInstanceRequest accountInstance);

    AccountInstanceResponse getAccountInstanceByAccountName(String accountName, String appId);

    AccountIdentifierResponse getAccountIdentifier(FindAccountIdentifierRequest findAccountIdentifierRequest);

    AccountInstanceResponse getAccountInstanceById(Long accountIdentifier);

    AccountOrgIdsResponse getOrgIdsByAccountId(Long accountInstanceId, boolean check, String appId);

    List<AccountRoleRelationResponse>  getRoleByAccountInstanceId(List<Long> principals,String appId);
}
