package org.dows.uim.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/resume")
@Tag(name = "管理端简历管理", description = "管理端简历管理")
@RequiredArgsConstructor
public class AdminResumeRest {

    @PostMapping("/import")
    @Operation(summary = "导入简历")
    public ImportResponse importResume(@RequestBody ImportRequest request) {
        // 实现逻辑
        return new ImportResponse(); // 示例返回值
    }

    @PostMapping("/relate")
    @Operation(summary = "关联简历")
    public void relateResume(@RequestParam @Parameter(description = "账号ID") Long accountInstanceId) {
        // 实现逻辑
    }

    @PostMapping("/analyze")
    @Operation(summary = "AI模型分析简历信息")
    public AnalyzeResponse analyzeResume(@RequestBody AnalyzeRequest request) {
        // 实现逻辑
        return new AnalyzeResponse(); // 示例返回值
    }

    @PostMapping("/parse")
    @Operation(summary = "解析简历")
    public ParseResponse parseResume(@RequestBody ParseRequest request) {
        // 实现逻辑
        return new ParseResponse(); // 示例返回值
    }
}

@Data
class ImportRequest {
    @Parameter(description = "人才简历ID")
    private Long resumeInstanceId;
}

@Data
class ImportResponse {
    @Parameter(description = "账号实例ID")
    private Long accountInstanceId;
}

@Data
class AnalyzeRequest {
    @Parameter(description = "人才简历ID")
    private Long resumeInstanceId;

    @Parameter(description = "简历信息")
    private ResumeInstance resumeInstance;
}

@Data
class AnalyzeResponse {
    @Parameter(description = "成功、失败")
    private Long result;
}

@Data
class ParseRequest {
    @Parameter(description = "人才简历ID")
    private Long resumeInstanceId;

    @Parameter(description = "简历信息")
    private ResumeInstance resumeInstance;
}

@Data
class ParseResponse {
    @Parameter(description = "成功、失败")
    private Long result;
}

@Data
class ResumeInstance {
    @Parameter(description = "姓名")
    private String name;

    @Parameter(description = "联系方式")
    private String contactInfo;
}