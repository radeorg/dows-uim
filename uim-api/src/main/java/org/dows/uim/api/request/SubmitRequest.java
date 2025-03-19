package org.dows.uim.api.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class SubmitRequest {
    @Parameter(description = "考试试题ID")
    private Long examQuestionId;

    @Parameter(description = "考试实例ID")
    private Long examInstanceId;

    @Parameter(description = "题项ID")
    private Long examItemId;

    @Parameter(description = "结果选项")
    private String itemAnswer;
}
