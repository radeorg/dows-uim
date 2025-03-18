package org.dows.uim.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/exam")
@Tag(name = "客户端考试管理", description = "客户端考试管理")
@RequiredArgsConstructor
public class ClientExamRest {

    @PostMapping("/submit")
    @Operation(summary = "在线考试提交")
    public SubmitResponse submit(@RequestBody SubmitRequest request) {
        // 实现逻辑
        return new SubmitResponse(); // 示例返回值
    }
}

@Data
class SubmitRequest {
    @Parameter(description = "考试试题ID")
    private Long examQuestionId;

    @Parameter(description = "考试实例ID")
    private Long examInstanceId;

    @Parameter(description = "题项ID")
    private Long examItemId;

    @Parameter(description = "结果选项")
    private String itemAnswer;
}

@Data
class SubmitResponse {
    @Parameter(description = "考得分值")
    private Integer examScore;
}