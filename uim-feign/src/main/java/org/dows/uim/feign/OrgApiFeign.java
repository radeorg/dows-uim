package org.dows.uim.feign;

import io.swagger.v3.oas.annotations.Operation;
import org.dows.uim.response.JobDescriptionResponse;
import org.dows.uim.response.JobIndicatorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "orgapiservice") // 服务名和URL
public interface OrgApiFeign {

    @GetMapping("/open/org/get/OrgIndictorByJobName")
    @Operation(summary = "通过岗位名称获取指标")
    JobIndicatorResponse getOrgIndicatorByJobName(@RequestParam String jobName);

    @GetMapping("/open/org/get/JobDescByJobName")
    @Operation(summary = "通过岗位名称获取岗位信息")
    JobDescriptionResponse getJobDescriptionByJobName(@RequestParam String jobName) ;
}
