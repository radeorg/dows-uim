package org.dows.uim.api;

import org.dows.uim.api.response.JobDescriptionResponse;
import org.dows.uim.api.response.JobIndicatorResponse;

public interface OrgApi {
    JobIndicatorResponse getOrgInditor(String jobName);

    JobDescriptionResponse getJobDescription(String jobName);
}
