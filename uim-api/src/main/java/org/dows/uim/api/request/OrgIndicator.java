package org.dows.uim.api.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class OrgIndicator {
    @Parameter(description = "岗位指标ID")
    private Long orgIndicatorId;

    @Parameter(description = "岗位行动ID")
    private Long orgActionId;

    @Parameter(description = "指标名称")
    private String indicatorName;

    @Parameter(description = "指标关键字")
    private String indicatorKeyword;

    @Parameter(description = "指标分值")
    private Integer indicatorScore;

    @Parameter(description = "指标匹配度")
    private Integer matchScore;
}
