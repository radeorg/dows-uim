package org.dows.uim.open;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.OrgApi;
import org.dows.uim.api.OrgEmailApi;
import org.dows.uim.biz.AccountApiBiz;
import org.dows.uim.biz.OrgApiBiz;
import org.dows.uim.biz.OrgEmailBiz;
import org.dows.uim.request.*;
import org.dows.uim.response.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/open/org")
@Tag(name = "组织管理接口", description = "组织管理接口")
@RequiredArgsConstructor
public class OrgApiRest implements OrgApi, OrgAccountApi, OrgEmailApi {
    private final OrgApiBiz orgApiBiz;
    private final AccountApiBiz accountApiBiz;
    private final OrgEmailBiz orgEmailBiz;

    @Operation(summary = "JD上架或下架")
    public OrgJobJDResponse upOrDownJd(@RequestBody OrgJDUpOrDownRequest orgJDUpOrDownRequest) throws UnavailableException {
        return orgApiBiz.upOrDownJd(orgJDUpOrDownRequest);
    }

    @Operation(summary = "通过岗位名称获取岗位信息")
    public JobDescriptionResponse getJobDescriptionByJobName(@RequestParam Long orgJdId,@RequestParam Long orgRootId, @RequestParam String jobName, @RequestParam String jobNo) {
        return orgApiBiz.getJobDescriptionByJobName(orgJdId,orgRootId, jobName,jobNo);
    }

    @Operation(summary = "通过规则ID获取指标")
    public JobIndicatorResponse getOrgIndicatorById(@RequestParam Long orgRootId, @RequestParam Long orgRuleId) {
        return orgApiBiz.getOrgIndicatorByIndicatorId(orgRootId, orgRuleId);
    }

    @Operation(summary = "保存JD信息")
    public OrgJobJDResponse saveOrgJdInfo(@RequestBody OrgJdSaveRequest orgJdSaveRequest) throws UnavailableException {
        return orgApiBiz.saveOrgJdInfo(orgJdSaveRequest);
    }

    @Operation(summary = "删除JD信息")
    public Boolean deleteJd(@RequestParam Long orgJdId) throws UnavailableException {
        return orgApiBiz.deleteJd(orgJdId);
    }

    @Operation(summary = "获取JD分页列表")
    public Page<OrgJobJDDetailResponse> getJdPage(OrgJdPageQueryRequest orgJdQueryRequest) throws UnavailableException{
        return orgApiBiz.getJdPage(orgJdQueryRequest);
    }

    @Operation(summary = "获取JD列表")
    public OrgJdListResponse getJdList(OrgJdQueryRequest orgJdQueryRequest) throws UnavailableException {
        return orgApiBiz.getJdList(orgJdQueryRequest);
    }

    @Operation(summary = "获取JDCode列表")
    public List<JdCodeResponse> getJdCodeList(@RequestBody HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        return orgApiBiz.getJdCodeList(hrmJdCodeQueryRequest);
    }

    @Operation(summary = "保存JDCode信息")
    public JdCodeResponse addJdCode(@RequestBody HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        return orgApiBiz.addJdCode(hrmJdCodeQueryRequest);
    }

    @Operation(summary = "保存岗位规则")
    public OrgRuleResponse saveOrgRule(@RequestBody OrgRuleSaveRequest orgRuleSaveRequest) {
        return orgApiBiz.saveOrgRule(orgRuleSaveRequest);
    }

    @Operation(summary = "保存岗位动作")
    public OrgActionResponse saveOrgRuleAction(@RequestBody OrgActionSaveRequest orgActionSaveRequest) {
        return orgApiBiz.saveOrgRuleAction(orgActionSaveRequest);
    }

    @Operation(summary = "保存岗位指标")
    public JobIndicatorResponse saveOrgRuleIndicator(@RequestBody OrgIndicatorListSaveRequest orgIndicatorListSaveRequest) {
        return orgApiBiz.saveOrgRuleIndicator(orgIndicatorListSaveRequest);
    }

    @Operation(summary = "通过岗位名称获取指标")
    public JobIndicatorResponse getOrgIndicatorByJobName(@RequestParam Long orgJdId,@RequestParam Long orgRootId, @RequestParam String jobName, @RequestParam String jobNo) {
        return orgApiBiz.getOrgIndicatorByJobName(orgJdId,orgRootId, jobName,jobNo);
    }

    @Operation(summary = "注册企业账号")
    @Override
    public List<OrgRegisterResponse> getOrgWithRegister(@RequestBody List<OrgRegisterRequest> orgRegisterRequest) {
        List<OrgRegisterResponse> orgWithRegisters = new ArrayList<>();
        for (OrgRegisterRequest registerRequest : orgRegisterRequest) {
            OrgRegisterResponse orgWithRegister = orgApiBiz.getOrgWithRegister(registerRequest);
            orgWithRegisters.add(orgWithRegister);
        }
        return orgWithRegisters;
    }

    @Operation(summary = "增加企业账号[招聘官,企业管理员,企业用户...]")
    public void saveOrgAccount(@RequestBody SaveOrgAccountRequest saveOrgAccountRequests) {
        accountApiBiz.saveOrgAccount(saveOrgAccountRequests);
    }

    @Operation(summary = "通过邮箱获取组织信息")
    public OrgRegisterResponse getOrgInfo(OrgRegisterRequest orgRegisterRequest) {
        return orgApiBiz.getOrgInfo(orgRegisterRequest);
    }

    @Override
    public List<RootOrgResponse> getRootOrgListByAccountInstanceId(Long accountInstanceId) {
        return orgApiBiz.getRootOrgListByAccountInstanceId(accountInstanceId);
    }

    @Override
    public List<OrgJdOrgRegisterInfoListResponse> getOrgJdOrgRegisterInfoList(OrgJdOrgRegisterInfoListRequest request) {
        return orgApiBiz.getOrgJdOrgRegisterInfoList(request);
    }

    @Override
    public OrgEmailResponse getEmailInfo(Long treeId){
        return orgEmailBiz.getOrgEmailInfo(treeId);
    }

    @Operation(summary = "通过组织树ID获取组织信息")
    public OrgRegisterResponse getContextRootOrg() {
        return orgApiBiz.getContextRootOrg();
    }
}

