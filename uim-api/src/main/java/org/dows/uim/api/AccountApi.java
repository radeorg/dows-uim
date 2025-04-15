package org.dows.uim.api;

import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.BindingAccountRequest;
import org.dows.uim.request.FindAccountIdentifierRequest;
import org.dows.uim.response.AccountIdentifierResponse;
import org.dows.uim.response.AccountInstanceResponse;
import org.dows.uim.response.AccountOrgIdsResponse;
import org.dows.uim.response.AccountRoleRelationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface AccountApi {


    /**
     * 绑定信息到账号
     * @param bindingAccountRequest
     */
    @PostMapping("/v1/open/uim/account/binding")
    default void bindingAccount(BindingAccountRequest bindingAccountRequest){

    }
    /**
     * 注册即创建
     *
     * @param accountInstanceRequest
     * @return
     */
    @PostMapping("/v1/open/uim/account/register")
    default Long getAccountWithRegister(AccountInstanceRequest accountInstanceRequest) {
        throw new UnsupportedOperationException("not class implement");
    }


    /**
     * 根据手机号注册即创建账号，如果账号已存在，则直接返回账号实例ID，不存在则创建账号并返回账号实例ID
     *
     * @param telephone
     * @return
     */
    @PostMapping("/v1/open/uim/account/telephone/register")
    default Long getAccountByTelephone(String telephone) {
        throw new UnsupportedOperationException("not class implement");
    }


    /**
     * 获取账号类型
     *
     * @param accountTypeRequest
     * @return
     */

    @GetMapping("/v1/open/uim/account/type/list")
    default List<AccountTypeResponse> getAccountType(AccountTypeRequest accountTypeRequest) {
        throw new UnsupportedOperationException("not class implement");
    }

    /**
     * 根据账号标识获取账号实例ID
     *
     * @param appId
     * @param identifier
     * @return
     */
    @GetMapping("/v1/open/uim/account/info")
    default AccountInstanceResponse getAccountInstanceByIdentifier(String appId, String identifier) {
        throw new UnsupportedOperationException("not class implement");
    }

    /**
     * 获取账号所有关联的角色ID(个人和所在组织所拥有的角色)
     *
     * @param appId
     * @return
     */
    @GetMapping("/v1/open/uim/account/role/list")
    default List<Long> getAllRoleIds(String appId, Long accountId) {
        throw new UnsupportedOperationException("not class implement");
    }


    /**
     * 根据账号ID集合获取账号实例集合
     *
     * @param appId
     * @param accountIds 账号ID集合
     * @param filters    过滤字段
     * @return
     */
    @GetMapping("/v1/open/uim/account/instance/list")
    default List<AccountInstanceResponse> getAccountInstanceByIds(String appId, List<Long> accountIds, List<String> filters) {
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
