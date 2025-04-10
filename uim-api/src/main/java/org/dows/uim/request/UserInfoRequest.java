package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoRequest {
    @Schema(description = "用户ID")
    private Long userInstanceId;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "身份证号")
    private String identifyNo;

    @Schema(description = "用户年龄")
    private Integer userAge;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "应用ID")
    private String appId;

    @Schema(description = "乐观锁, 默认: 0")
    private Integer ver;

    @Schema(description = "时间戳")
    private Date ts;
}
