package org.dows.uim.api;

import org.dows.uim.api.response.JobDescriptionResponse;
import org.dows.uim.api.response.JobIndicatorResponse;
import org.dows.uim.api.response.OrgInfoResponse;

public interface OrgApi {
    JobIndicatorResponse getOrgInditor(String jobName);

    JobDescriptionResponse getJobDescription(String jobName);
    OrgInfoResponse getOrgInfo(Long orgId);
}
