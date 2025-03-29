package org.dows.uim.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.AccountApi;
import org.dows.uim.api.request.AccountInstanceRequest;
import org.dows.uim.api.response.AccountInstanceResponse;
import org.dows.uim.biz.AccountApiBiz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open/account")
@Tag(name = "账户管理接口", description = "账户管理接口")
@RequiredArgsConstructor
public class AccountApiRest implements AccountApi{
    private final AccountApiBiz accountApiBiz;

    @GetMapping("/get/AccountInstance")
    @Operation(summary = "通过账户信息获取账户实例")
    public AccountInstanceResponse getAccountInstanceByAccountName(String appId, String accountName){
        return accountApiBiz.getAccountInstanceByAccountName(appId, accountName);
    }

    @GetMapping("/set/AccountInstance")
    @Operation(summary = "保存账户实例")
    public Long setAccountInstance(String appId, AccountInstanceRequest accountInstance) {
        return accountApiBiz.setAccountInstance(appId, accountInstance);
    }

    @GetMapping("/get/RoleIds")
    @Operation(summary = "通过账户实例获取角色ID列表")
    public List<Long> getAllRoleIds(String appId, Long accountInstanceId){
        return accountApiBiz.getAllRoleIds(appId, accountInstanceId);
    }

    @GetMapping("/get/ById")
    @Operation(summary = "通过账户标识符获取账户实例")
    public AccountInstanceResponse getAccountInstanceById(String appId, Long accountIdentifier) {
        return accountApiBiz.getAccountInstanceById(appId, accountIdentifier);
    }
}

