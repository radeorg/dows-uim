package org.dows.uim.open;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.TenantLockApi;
import org.dows.uim.service.TenantLockService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "后台管理招聘官管理", description = "后台管理招聘官管理")
@RequiredArgsConstructor
public class TenantLockRest implements TenantLockApi {

    private final TenantLockService tenantLockService;
    @Override
    public boolean isLocked(String appId) {
        //tenantLockService.
        return false;
    }
}
