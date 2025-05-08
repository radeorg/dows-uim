package org.dows.uim.feign;

import org.dows.uim.api.OrgApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "uim", configuration = UimFeignConfig.class) // 服务名和URL
public interface OrgApiFeign extends OrgApi {


}
