package org.dows.uim.api;

import org.dows.uim.api.response.JobDescriptionResponse;
import org.dows.uim.api.response.JobIndicatorResponse;

public interface OrgApi {
    default JobIndicatorResponse getOrgIndicatorByJobName(String jobName)
    {
        throw new UnsupportedOperationException("not class implement");
    }

    default JobDescriptionResponse getJobDescriptionByJobName(String jobName) {
        throw new UnsupportedOperationException("not class implement");
    }
}
