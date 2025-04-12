package org.dows.uim.api;

import org.dows.uim.request.OrgRegisterRequest;
import org.dows.uim.response.JobDescriptionResponse;
import org.dows.uim.response.JobIndicatorResponse;
import org.dows.uim.response.OrgRegisterResponse;
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
}
