package org.dows.uim.feign;

import org.dows.uim.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "uim-account", configuration = UimFeignConfig.class) // 服务名和URL
public interface AccountApiFeign extends AccountApi {


}
