package org.dows.uim.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/org")
@Tag(name = "用户端组织管理", description = "用户端组织管理")
@RequiredArgsConstructor
public class UserOrgRest {

    @GetMapping("/job/search")
    @Operation(summary = "用户搜索岗位")
    public List<JobSearchResponse> jobSearch(
            @RequestParam(value = "jdKeyword", required = false) @Parameter(description = "岗位关键字") String jdKeyword,
            @RequestParam(value = "tsStart", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Parameter(description = "时间戳开始") Date tsStart,
            @RequestParam(value = "tsEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @Parameter(description = "时间戳结束") Date tsEnd) {
        // 实现逻辑
        return List.of(new JobSearchResponse()); // 示例返回值
    }

    @PostMapping("/resume/post")
    @Operation(summary = "用户投递简历")
    public Long resumePost(@RequestBody ResumePostRequest request) {
        // 实现逻辑
        return 1L; // 示例返回值
    }
}

@Data
class JobSearchResponse {
    @Parameter(description = "岗位描述ID")
    private Long orgJdId;

    @Parameter(description = "岗位描述")
    private String description;

    @Parameter(description = "时间戳")
    private Date ts;
}

@Data
class ResumePostRequest {
    @Parameter(description = "岗位描述ID")
    private Long orgJdId;

    @Parameter(description = "简历链接")
    private String resumeLink;

    @Parameter(description = "简历文件")
    private String resumeDoc;
}