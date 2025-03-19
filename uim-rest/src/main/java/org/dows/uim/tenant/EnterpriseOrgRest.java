package org.dows.uim.tenant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.request.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise/org")
@Tag(name = "企业端组织管理", description = "企业端组织管理")
@RequiredArgsConstructor
public class EnterpriseOrgRest {

    @PostMapping("/register")
    @Operation(summary = "企业登记注册")
    public Long register(@RequestBody RegisterRequest request) {
        // 实现逻辑
        return 1L; // 示例返回值
    }
}

