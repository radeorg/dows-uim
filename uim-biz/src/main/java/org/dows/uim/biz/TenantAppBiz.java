package org.dows.uim.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.handler.TenantAppHandler;
import org.dows.uim.handler.TenantInstanceHandler;
import org.dows.uim.request.TenantAppRequest;
import org.dows.uim.request.TenantInstanceRequest;
import org.dows.uim.response.TenantAppResponse;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tangsm
 * @data 2025/7/19 星期六
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TenantAppBiz {

    private final TenantAppHandler tenantAppHandler;
    private final TenantInstanceHandler tenantInstanceHandler;

    public String initAppId(){
        return tenantAppHandler.initAppId();
    }

    @Transactional
    public TenantAppResponse save(TenantAppRequest request)  {
        TenantInstanceRequest tenantInstanceRequest = new TenantInstanceRequest();
        tenantInstanceRequest.setAccountInstanceId(request.getAccountInstanceId());
        tenantInstanceRequest.setCompanyName(request.getCompanyName());
        tenantInstanceRequest.setAppId(request.getAppId());
        Long tenantInstanceId = tenantInstanceHandler.save(tenantInstanceRequest);

        request.setTenantInstanceId(tenantInstanceId);
        return tenantAppHandler.save(request);
    }

    public String getAppIdByNamespace(String namespace) {
       return tenantAppHandler.getAppIdByNamespace(namespace);
    }

    public String getNamespaceByAppId(String appId) {
        return tenantAppHandler.getNamespaceByAppId(appId);
    }

    public List<String> listAppId() {
        return tenantAppHandler.listAppId();
    }
}
