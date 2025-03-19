package org.dows.uim.api.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class RegisterRequest {
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
