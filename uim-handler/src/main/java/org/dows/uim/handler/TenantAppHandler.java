package org.dows.uim.handler;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.crud.AppIdIgnoreUtils;
import org.dows.uim.entity.TenantAppEntity;
import org.dows.uim.request.TenantAppRequest;
import org.dows.uim.response.TenantAppResponse;
import org.dows.uim.service.TenantAppService;
import org.dows.uim.util.CompanyNameUtil;
import org.dows.uim.util.DateTimeFormatterUtil;
import org.dows.uim.util.NumberIncrementUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author tangsm
 * @data 2025/7/27 星期日
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class TenantAppHandler {

    private final TenantAppService tenantAppsService;

    public String initAppId(){
        return getAppId();
    }

    public TenantAppResponse save(TenantAppRequest request)  {
        TenantAppEntity entity = new TenantAppEntity();
        entity.setOrgRegisterId(request.getOrgRegisterId());
        entity.setTenantInstanceId(request.getTenantInstanceId());
        entity.setAppId(request.getAppId());
        entity.setNamespace(getCompanyEnName(request.getCompanyName()));
        entity.setOperatorId(request.getAccountInstanceId());
        tenantAppsService.save(entity);
        return BeanUtil.copyProperties(entity, TenantAppResponse.class);
    }

    public String getAppIdByNamespace(String namespace) {
        TenantAppEntity entity = tenantAppsService.getOne(QueryWrapper.create()
                .eq(TenantAppEntity::getNamespace, namespace));
        return entity != null ? entity.getAppId() : null;
    }

    public String getNamespaceByAppId(String appId) {
        TenantAppEntity entity = tenantAppsService.getOne(QueryWrapper.create()
                .eq(TenantAppEntity::getAppId, appId));
        return entity != null ? entity.getNamespace() : null;
    }

    public List<String> listAppId() {
        List<TenantAppEntity> entities = tenantAppsService.list(QueryWrapper.create()
                .select("DISTINCT app_id"));
        return entities.stream()
                .map(TenantAppEntity::getAppId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private String getCompanyEnName(String companyName) {
        String enName = CompanyNameUtil.getFirstLetters(companyName);

        TenantAppEntity entity = getByNamespaceAndIgnoreAppId(enName);
        if (entity != null) {
            // 如果存在，则再检查是否有含序号的名称，^表示字符串开头，[0-9]{2}表示两位数字，$表示字符串结尾
            entity = tenantAppsService.getOne(QueryWrapper.create()
                    .where("namespace REGEXP ?", "^" + enName + "[0-9]{2}$"));
            if (entity != null) {
                // 数字+1返回，如：ABC01 -> ABC02
                return NumberIncrementUtil.incrementTrailingNumber(entity.getNamespace());
            } else {
                return enName + "01";
            }
        }

        return enName;
    }

    private String getAppId() {
        return DateTimeFormatterUtil.generateTimestamp();
    }

    /**
     * 不带appId查询
     */
    private TenantAppEntity getByNamespaceAndIgnoreAppId(String namespace) {
        AtomicReference<TenantAppEntity> holder = new AtomicReference<>();
        AppIdIgnoreUtils.executeWithoutTenant(() -> {
            TenantAppEntity entity = tenantAppsService.getOne(QueryWrapper.create()
                    .eq(TenantAppEntity::getNamespace, namespace));

            holder.set(entity);
        });
        return holder.get();
    }
}
