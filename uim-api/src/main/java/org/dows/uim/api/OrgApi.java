package org.dows.uim.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.UnavailableException;
import org.dows.rade.web.Response;
import org.dows.uim.request.*;
import org.dows.uim.request.JdKeyWord.JDSaveRequest;
import org.dows.uim.response.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrgApi {
    @GetMapping("/v1/open/uim/org/jd/get")
    @Operation(summary = "通过岗位名称获取岗位信息")
    default JobDescriptionResponse getJobDescriptionByJobName(@RequestParam String jobName){
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/open/uim/org/jd/indictor/get")
    @Operation(summary = "通过岗位名称获取岗位指标")
    default JobIndicatorResponse getOrgIndicatorByJobName(@RequestParam String jobName){
        throw new UnsupportedOperationException("not class implement");
    }



    @GetMapping("/v1/uim/org/job/indicator/info")
    default JobIndicatorResponse getOrgIndicatorByJobName(Long orgJdId,Long orgRootId, String jobName, String jdNo) {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/org/job/info")
    default JobDescriptionResponse getJobDescriptionByJobName(Long orgJdId,Long orgRootId, String jobName, String jdNo) {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/job/indicator/get")
    default JobIndicatorResponse getOrgIndicatorById(Long orgRootId, Long orgRuleId) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/jd/get")
    @Operation(summary = "通过岗位ID获取岗位信息")
    default OrgJdResponse getOrgJdById(Long orgJdId) {
        throw new UnsupportedOperationException("not class implement");
    }

    @Operation(summary = "通过岗位名称关键词获取岗位信息")
    default List<OrgJdResponse> listOrgJdByKeyword(String keyword, String appId) {
        throw new UnsupportedOperationException("not class implement");
    }

    @Operation(summary = "通过appId获取岗位集合")
    default List<OrgJdResponse> listOrgJdByAppId(String appId) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/jd/get/jdNo")
    @Operation(summary = "通过岗位编号获取岗位信息")
    default OrgJdResponse getOrgJdByJdNo(String jdNo) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/jd/state/update")
    default OrgJobJDResponse upOrDownJd(OrgJDUpOrDownRequest orgJDUpOrDownRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/jd/save")
    default OrgJobJDResponse saveOrgJdInfo(OrgJdSaveRequest orgJdSaveRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }


    @DeleteMapping("/v1/uim/org/jd/delete")
    default Boolean deleteJd(Long orgJdId) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/org/jd/list")
    default OrgJdListResponse getJdList(OrgJdQueryRequest orgJdQueryRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/jdCode/list")
    default List<JdCodeResponse> getJdCodeList(HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/jdCode/getOne")
    default JdCodeResponse getJdCodeOne(HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/jdCode/add")
    default JdCodeResponse addJdCode(HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/org/jd/page")
    default Page<OrgJobJDDetailResponse> getJdPage(OrgJdPageQueryRequest orgJdQueryRequest) throws UnavailableException{
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/org/jd/select")
    default List<OrgJdSelectorResponse> getJdSelect(){
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/rule/save")
    default OrgRuleResponse saveOrgRule(OrgRuleSaveRequest orgRuleSaveRequest) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/action/save")
    default OrgActionResponse saveOrgRuleAction(OrgActionSaveRequest orgActionSaveRequest) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/indicator/save")
    default JobIndicatorResponse saveOrgRuleIndicator(OrgIndicatorListSaveRequest orgIndicatorListSaveRequest) {
        throw new UnsupportedOperationException("not class implement");
    }

    /**
     * 注册企业账号
     *
     * @param orgRegisterRequest
     * @return
     */
    @PostMapping("v1/uim/org/register")
    default List<OrgRegisterResponse> getOrgWithRegister(List<OrgRegisterRequest> orgRegisterRequest) {
        throw new UnsupportedOperationException("not class implement");
    }


    /**
     * 注册企业账号
     *
     * @param orgRegisterRequest
     * @return
     */
    @PostMapping("v1/uim/org/register/info")
    OrgRegisterResponse getOrgInfo(@RequestBody OrgRegisterRequest orgRegisterRequest) ;/*{
        throw new UnsupportedOperationException("not class implement");
    }*/
    default List<RootOrgResponse> getRootOrgListByAccountInstanceId(Long accountInstanceId){
        throw new UnsupportedOperationException("not class implement");
    }

    default List<OrgJdOrgRegisterInfoListResponse> getOrgJdOrgRegisterInfoList(OrgJdOrgRegisterInfoListRequest request) {
        throw new UnsupportedOperationException("not class implement");
    }

    /**
     * 根据当前登录人获取所属组织信息
     * @return RootOrgResponse
     */
    @GetMapping("/v1/uim/org/info")
    default OrgRegisterResponse getContextRootOrg(){
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/jdinfo/save")
    default Response saveOrgJd(JDSaveRequest saveRequest) throws UnavailableException, JsonProcessingException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/jdCode/codeList")
    default List<JdCodeResponse> getJdCodeByList(HrmJdCodeQueryListRequest hrmJdCodeQueryRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/company/query")
    default CompanyInfoResponse queryCompany() throws UnavailableException, JsonProcessingException {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/open/uim/org/jdinfo/query")
    default JDInfoResponse queryJdInfo(Long orgJdId) throws JsonProcessingException {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/open/uim/org/jdinfo/queryEntity")
    default OrgJobJDResponse queryJdEntity(String jdNo) throws JsonProcessingException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/jdinfo/list")
    default List<JDInfoResponse> queryJdInfoList(List<Long> orgJdIds) throws JsonProcessingException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/open/uim/org/jdinfo/update")
    default Response updateOrgJd(JDSaveRequest saveRequest) throws UnavailableException, JsonProcessingException {
        throw new UnsupportedOperationException("not class implement");
    }

}
