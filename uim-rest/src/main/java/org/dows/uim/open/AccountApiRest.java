package org.dows.uim.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.AccountApi;
import org.dows.uim.api.AccountTypeRequest;
import org.dows.uim.api.AccountTypeResponse;
import org.dows.uim.biz.AccountApiBiz;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.BindingAccountRequest;
import org.dows.uim.response.AccountInstanceResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

}

