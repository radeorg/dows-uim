package org.dows.uim.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.rade.constant.IdentifierType;
import org.dows.uim.api.AccountApi;
import org.dows.uim.api.AccountTypeRequest;
import org.dows.uim.api.AccountTypeResponse;
import org.dows.uim.biz.AccountApiBiz;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.BindingAccountRequest;
import org.dows.uim.request.RelevancyAccountInstanceIdForOpenidByTelephoneRequest;
import org.dows.uim.response.AccountInstanceResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "账号管理", description = "账户管理")
@RequiredArgsConstructor
public class AccountApiRest implements AccountApi{
    private final AccountApiBiz accountApiBiz;


    @Operation(summary = "保存注册账户实例")
    public Long getAccountWithRegister(@RequestBody AccountInstanceRequest accountInstance) {
        return accountApiBiz.accountRegister(accountInstance);
    }


    /**
     * 根据手机号注册即创建账号，如果账号已存在，则直接返回账号实例ID，不存在则创建账号并返回账号实例ID
     *
     * @param telephone
     * @return
     */
    @Operation(summary = "根据手机号注册即创建账号，如果账号已存在，则直接返回账号实例ID，不存在则创建账号并返回账号实例ID")
    public Long getAccountByTelephone(String telephone) {
        return accountApiBiz.getAccountByTelephone(telephone);
    }


    @Operation(summary = "通过账户标识符获取账户实例")
    public AccountInstanceResponse getAccountInstanceByIdentifier(@RequestParam String appId, @RequestParam String accountIdentifier) {
        return accountApiBiz.getAccountInstanceByIdentifier(appId, accountIdentifier);
    }


    @Operation(summary = "绑定信息到当前账号")
    public void bindingAccount(@RequestBody @Validated BindingAccountRequest bindingAccountRequest) {
        accountApiBiz.bindingAccount(bindingAccountRequest);
    }

    @Operation(summary = "获取账号类型列表")
    public List<AccountTypeResponse> getAccountType(AccountTypeRequest accountTypeRequest) {
        return accountApiBiz.getAccountType(accountTypeRequest);
    }


    @Operation(summary = "通过账户实例获取角色ID列表")
    public List<Long> getAllRoleIds(@RequestParam String appId, @RequestParam Long accountInstanceId){
        return accountApiBiz.getAllRoleIds(appId, accountInstanceId);
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
    public List<AccountInstanceResponse> getAccountInstanceByIds(String appId, List<Long> accountIds, List<String> filters) {
        return accountApiBiz.getAccountInstanceByIds(appId, accountIds, filters);
    }

    /**
     * 增加账号标识符
     *
     * @param identifier
     * @param identifierType
     * @return
     */
    @Override
    public Long addAccountIdentifier(String identifier, IdentifierType identifierType) {
         return accountApiBiz.addAccountIdentifier(identifier, identifierType);
    }

    public void relevancyAccountInstanceIdForOpenidByTelephone(RelevancyAccountInstanceIdForOpenidByTelephoneRequest
                                                                        relevancyAccountInstanceIdByTelephoneRequest) {
        accountApiBiz.relevancyAccountInstanceIdForOpenidByTelephone(relevancyAccountInstanceIdByTelephoneRequest);

    }
}

