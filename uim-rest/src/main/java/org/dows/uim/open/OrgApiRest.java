package org.dows.uim.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.OrgApi;
import org.dows.uim.biz.AccountApiBiz;
import org.dows.uim.biz.OrgApiBiz;
import org.dows.uim.request.AddOrgAccountRequest;
import org.dows.uim.request.OrgRegisterRequest;
import org.dows.uim.response.AddOrgAccountResponse;
import org.dows.uim.response.JobDescriptionResponse;
import org.dows.uim.response.JobIndicatorResponse;
import org.dows.uim.response.OrgRegisterResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/open/org")
@Tag(name = "组织管理接口", description = "组织管理接口")
@RequiredArgsConstructor
public class OrgApiRest implements OrgApi, OrgAccountApi {
    private final OrgApiBiz orgApiBiz;
    private final AccountApiBiz accountApiBiz;


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
    public List<OrgRegisterResponse> getOrgWithRegister(@RequestBody List<OrgRegisterRequest> orgRegisterRequest) {
        return orgApiBiz.getOrgWithRegister(orgRegisterRequest);
    }

    @Operation(summary = "增加企业账号[招聘官,企业管理员,企业用户...]")
    public List<AddOrgAccountResponse> saveOrgAccount(@RequestBody List<AddOrgAccountRequest> addOrgAccountRequests) {
        return accountApiBiz.saveOrgAccount(addOrgAccountRequests);
    }

    @Operation(summary = "通过邮箱获取组织信息")
    public OrgRegisterResponse getOrgInfo(OrgRegisterRequest orgRegisterRequest) {
        return orgApiBiz.getOrgInfo(orgRegisterRequest);
    }

}

