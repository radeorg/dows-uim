package org.dows.uim;

import com.mybatisflex.core.tenant.TenantFactory;
import org.dows.rade.context.AppContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangsm
 * @data 2025/5/24 星期六
 * 执行查询、删除、更新操作时，自动附加appId条件过滤
 */
@Configuration
public class AppIdConfig {
    @Bean
    public TenantFactory tenantFactory() {
        return () -> {
            String appId = AppContext.getAppId();
            return appId != null ? new Object[]{appId} : new Object[0];
        };
    }
}
