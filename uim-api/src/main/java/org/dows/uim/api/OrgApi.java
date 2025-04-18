package org.dows.uim.api;

import jakarta.servlet.UnavailableException;
import org.dows.uim.request.*;
import org.dows.uim.response.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface OrgApi {

    @GetMapping("/v1/uim/job/indicator/info")
    default JobIndicatorResponse getOrgIndicatorByJobName(Long orgRootId, String jobName) {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/job/info")
    default JobDescriptionResponse getJobDescriptionByJobName(Long orgRootId, String jobName) {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/job/indicator/infobyid")
    default JobIndicatorResponse getOrgIndicatorById(Long orgRootId, Long orgRuleId) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/jd/upordown/info")
    default OrgJobJDResponse upOrDownJd(OrgJDUpOrDownRequest orgJDUpOrDownRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/jd/save/info")
    default OrgJobJDResponse saveOrgJdInfo(OrgJdSaveRequest orgJdSaveRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/jd/get/list")
    default OrgJdListResponse getJdList(OrgJdQueryRequest orgJdQueryRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/rule/save/info")
    default OrgRuleResponse saveOrgRule(OrgRuleSaveRequest orgRuleSaveRequest) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/ruleaction/save/info")
    default OrgActionResponse saveOrgRuleAction(OrgActionSaveRequest orgActionSaveRequest) {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/ruleindicator/save/info")
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
