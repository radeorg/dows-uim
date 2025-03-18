package org.dows.uim.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/exam")
@Tag(name = "管理端考试管理", description = "管理端考试管理")
@RequiredArgsConstructor
public class AdminExamRest {

    @PostMapping("/paper/config")
    @Operation(summary = "配置试卷")
    public PaperConfigResponse paperConfig(@RequestBody List<PaperConfigRequest> requests) {
        // 实现逻辑
        return new PaperConfigResponse(); // 示例返回值
    }
}

@Data
class PaperConfigRequest {
    @Parameter(description = "考试配置ID")
    private Long examConfigId;

    @Parameter(description = "试题题项ID")
    private Long examItemId;
}

@Data
class PaperConfigResponse {
    @Parameter(description = "试卷ID")
    private Long examPaperId;

    @Parameter(description = "试卷链接")
    private String link;
}