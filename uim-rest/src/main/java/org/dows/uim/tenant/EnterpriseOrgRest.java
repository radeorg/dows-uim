package org.dows.uim.tenant;

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

@Data
class RegisterRequest {
    @Parameter(description = "组织名称")
    private String orgName;

    @Parameter(description = "地址信息")
    private String address;

    @Parameter(description = "人员规模")
    private Integer headcount;

    @Parameter(description = "邮箱账号")
    private String email;

    @Parameter(description = "组织类型[0:企业,1:部门]")
    private Integer orgType;
}