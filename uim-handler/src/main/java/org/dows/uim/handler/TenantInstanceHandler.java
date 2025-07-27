package org.dows.uim.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.entity.TenantInstanceEntity;
import org.dows.uim.request.TenantInstanceRequest;
import org.dows.uim.service.TenantInstanceService;
import org.springframework.stereotype.Component;

/**
 * @author tangsm
 * @data 2025/7/27 星期日
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class TenantInstanceHandler {

    private final TenantInstanceService tenantInstanceService;

    public Long save(TenantInstanceRequest request)  {
        TenantInstanceEntity entity = new TenantInstanceEntity();
        entity.setAccountInstanceId(request.getAccountInstanceId());
        entity.setAppId(request.getAppId());
        entity.setTenantName(request.getCompanyName());
        entity.setOperatorId(request.getAccountInstanceId());
        tenantInstanceService.save(entity);
        return entity.getTenantInstanceId();
    }
}
