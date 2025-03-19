package org.dows.uim.api.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class ParseRequest {
    @Parameter(description = "人才简历ID")
    private Long resumeInstanceId;

    @Parameter(description = "简历信息")
    private ResumeInstance resumeInstance;
}
