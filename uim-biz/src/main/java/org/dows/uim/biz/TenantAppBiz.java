package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.stream.Collectors;

/**
 * @author tangsm
 * @data 2025/7/19 星期六
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TenantAppBiz {

    private final TenantAppService tenantAppsService;

    public String initAppId(){
        return getAppId();
    }

    public TenantAppResponse save(TenantAppRequest request)  {
        TenantAppEntity entity = new TenantAppEntity();
        entity.setOrgRegisterId(request.getOrgRegisterId());
        entity.setAppId(request.getAppId());
        entity.setNamespace(getCompanyEnName(request.getCompanyName()));
        tenantAppsService.save(entity);
        return BeanUtil.copyProperties(entity, TenantAppResponse.class);
    }

    public String getAppIdByNamespace(String namespace) {
        TenantAppEntity entity = tenantAppsService.getOne(QueryWrapper.create()
                .eq(TenantAppEntity::getNamespace, namespace));
        return entity != null ? entity.getAppId() : null;
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

        TenantAppEntity entity = tenantAppsService.getOne(QueryWrapper.create()
                .eq(TenantAppEntity::getNamespace, enName));
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
}
