package org.dows.uim.tenant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.api.TenantAppApi;
import org.dows.uim.biz.TenantAppBiz;
import org.dows.uim.request.TenantAppRequest;
import org.dows.uim.response.TenantAppResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TenantAppRest implements TenantAppApi {

    private final TenantAppBiz tenantAppBiz;

    @Override
    public TenantAppResponse save(TenantAppRequest request) {
        return tenantAppBiz.save(request);
    }

    @Override
    public String getAppIdByNamespace(String namespace) {
        return tenantAppBiz.getAppIdByNamespace(namespace);
    }

    @Override
    public List<String> listAppId() {
        return tenantAppBiz.listAppId();
    }
}
