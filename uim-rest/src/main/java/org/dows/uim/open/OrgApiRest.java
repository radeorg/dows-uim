package org.dows.uim.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.OrgApi;
import org.dows.uim.biz.OrgApiBiz;
import org.dows.uim.request.OrgRegisterRequest;
import org.dows.uim.response.JobDescriptionResponse;
import org.dows.uim.response.JobIndicatorResponse;
import org.dows.uim.response.OrgRegisterResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open/org")
@Tag(name = "组织管理接口", description = "组织管理接口")
@RequiredArgsConstructor
public class OrgApiRest implements OrgApi {
    private final OrgApiBiz orgApiBiz;


    @Operation(summary = "通过岗位名称获取指标")
    public JobIndicatorResponse getOrgIndicatorByJobName(@RequestParam String jobName) {
        return orgApiBiz.getOrgIndicatorByJobName(jobName);
    }


    @Operation(summary = "通过岗位名称获取岗位信息")
    public JobDescriptionResponse getJobDescriptionByJobName(@RequestParam String jobName) {
        return orgApiBiz.getJobDescriptionByJobName(jobName);
    }

    @Operation(summary = "注册企业账号")
    @Override
    public List<OrgRegisterResponse> getOrgWithRegister(List<OrgRegisterRequest> orgRegisterRequest) {
        return orgApiBiz.getOrgWithRegister(orgRegisterRequest);
    }


}

