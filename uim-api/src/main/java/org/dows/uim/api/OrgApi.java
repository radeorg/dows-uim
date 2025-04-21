package org.dows.uim.api;

import jakarta.servlet.UnavailableException;
import org.dows.uim.request.*;
import org.dows.uim.response.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface OrgApi {

    @GetMapping("/v1/uim/org/job/indicator/info")
    default JobIndicatorResponse getOrgIndicatorByJobName(Long orgRootId, String jobName) {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/org/job/info")
    default JobDescriptionResponse getJobDescriptionByJobName(Long orgRootId, String jobName) {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/job/indicator/get")
    default JobIndicatorResponse getOrgIndicatorById(Long orgRootId, Long orgRuleId) {
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

    @GetMapping("/v1/uim/org/jd/list")
    default OrgJdListResponse getJdList(OrgJdQueryRequest orgJdQueryRequest) throws UnavailableException {
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
    @GetMapping("v1/uim/org/register/info")
    default OrgRegisterResponse getOrgInfo(OrgRegisterRequest orgRegisterRequest) {
        throw new UnsupportedOperationException("not class implement");
    }

    default List<RootOrgResponse> getRootOrgListByAccountInstanceId(Long accountInstanceId){
        throw new UnsupportedOperationException("not class implement");
    }
}
