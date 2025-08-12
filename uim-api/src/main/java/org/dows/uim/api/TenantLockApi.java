package org.dows.uim.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface TenantLockApi {
    /**
     * 检查是否被锁定
     *
     * @param appId 应用ID
     * @return 是否被锁定
     */
    @GetMapping("/v1/uim/tenant/lock/locked")
    boolean isLocked(String appId);
}
