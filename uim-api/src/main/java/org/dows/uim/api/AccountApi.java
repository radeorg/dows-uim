package org.dows.uim.api;

import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.FindAccountIdentifierRequest;
import org.dows.uim.response.AccountIdentifierResponse;
import org.dows.uim.response.AccountInstanceResponse;
import org.dows.uim.response.AccountOrgIdsResponse;
import org.dows.uim.response.AccountRoleRelationResponse;

import java.util.List;

public interface AccountApi {

    /**
     * 注册即创建
     *
     * @param accountInstanceRequest
     * @return
     */
    default Long getAccountWithRegister(AccountInstanceRequest accountInstanceRequest) {
        throw new UnsupportedOperationException("not class implement");
    }


    /**
     * 根据账号标识获取账号实例ID
     *
     * @param appId
     * @param identifier
     * @return
     */
    default AccountInstanceResponse getAccountInstanceByIdentifier(String appId, String identifier) {
        throw new UnsupportedOperationException("not class implement");
    }


    default AccountInstanceResponse getAccountInstanceById(Long accountInstanceId) {
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
     * 根据组织ID（orgNodeId）获取对应组织角色ID集合
     *
     * @param appId
     * @return
     */
    default List<Long> getOrgRoleIdsByOrgId(String appId, Long orgNodeId) {
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


//    default Long setAccountInstance(String appId, AccountInstanceRequest accountInstance) {
//        throw new UnsupportedOperationException("not class implement");
//    }


    default AccountIdentifierResponse getAccountIdentifier(FindAccountIdentifierRequest findAccountIdentifierRequest) {
        throw new UnsupportedOperationException("not class implement");
    }


    default AccountOrgIdsResponse getOrgIdsByAccountId(Long accountInstanceId, boolean check, String appId) {
        throw new UnsupportedOperationException("not class implement");
    }


    default List<AccountRoleRelationResponse> getRoleByAccountInstanceId(List<Long> principals, String appId) {
        throw new UnsupportedOperationException("not class implement");
    }

}
