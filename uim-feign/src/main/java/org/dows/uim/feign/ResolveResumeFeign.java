package org.dows.uim.feign;

import org.dows.uim.api.ResolveResumeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient
public interface ResolveResumeFeign extends ResolveResumeApi {

}
