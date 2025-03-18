package org.dows.uim.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/interview")
@Tag(name = "管理端面试管理", description = "管理端面试管理")
@RequiredArgsConstructor
public class AdminInterviewRest {

    @PostMapping("/analyze")
    @Operation(summary = "面试结果分析")
    public InterviewAnalyzeResponse interviewAnalyze(@RequestBody InterviewAnalyzeRequest request) {
        // 实现逻辑
        return new InterviewAnalyzeResponse(); // 示例返回值
    }

    @GetMapping("/get")
    @Operation(summary = "查看面试结果分析")
    public InterviewGetResponse interviewGet(@RequestParam @Parameter(description = "人才简历ID") Long resumeInstanceId) {
        // 实现逻辑
        return new InterviewGetResponse(); // 示例返回值
    }
}

@Data
@Schema(name = "InterviewAnalyzeRequest", title = "面试结果分析")
class InterviewAnalyzeRequest {
    @Schema(description = "人才简历ID")
    private Long resumeInstanceId;

    @Schema(description = "面试结果ID")
    private Long interviewResultId;
}

@Data
class InterviewAnalyzeResponse {
    @Schema(description = "面试统计ID")
    private Long interviewStatisticsId;
}

@Data
class InterviewGetRequest {
    @Schema(description = "人才简历ID")
    private Long resumeInstanceId;
}

@Data
class InterviewGetResponse {
    @Schema(description = "面试统计ID")
    private Long interviewStatisticsId;

    @Schema(description = "面试结果ID")
    private Long interviewResultId;
}