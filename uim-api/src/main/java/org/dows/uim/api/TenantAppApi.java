package org.dows.uim.api;

import org.dows.uim.request.TenantAppRequest;
import org.dows.uim.response.TenantAppResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TenantAppApi {

    @PostMapping("/v1/uim/tenant/app/save")
    TenantAppResponse save(@RequestBody TenantAppRequest request);

    String getAppIdByNamespace(String namespace);

    List<String> listAppId();
}
