package org.dows.uim.api;

import org.dows.uim.api.response.JobDescriptionResponse;
import org.dows.uim.api.response.JobIndicatorResponse;

public interface OrgApi {
    JobIndicatorResponse getOrgIndicatorByJobName(String jobName);

    JobDescriptionResponse getJobDescriptionByJobName(String jobName);
}
