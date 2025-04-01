package org.dows.uim.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.AccountApi;
import org.dows.uim.api.request.AccountInstanceRequest;
import org.dows.uim.api.response.AccountInstanceResponse;
import org.dows.uim.biz.AccountApiBiz;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open/account")
@Tag(name = "账户管理接口", description = "账户管理接口")
@RequiredArgsConstructor
public class AccountApiRest implements AccountApi{
    private final AccountApiBiz accountApiBiz;

    @PostMapping("/register")
    @Operation(summary = "保存注册账户实例")
    public Long getAccountWithRegister(@RequestBody AccountInstanceRequest accountInstance) {
        return accountApiBiz.accountRegister(accountInstance);
    }

    @GetMapping("/roles")
    @Operation(summary = "通过账户实例获取角色ID列表")
    public List<Long> getAllRoleIds(@RequestParam String appId, @RequestParam Long accountInstanceId){
        return accountApiBiz.getAllRoleIds(appId, accountInstanceId);
    }

    @GetMapping("/info")
    @Operation(summary = "通过账户标识符获取账户实例")
    public AccountInstanceResponse getAccountInstanceByIdentifier(@RequestParam String appId, @RequestParam String accountIdentifier) {
        return accountApiBiz.getAccountInstanceByIdentifier(appId, accountIdentifier);
    }
}

