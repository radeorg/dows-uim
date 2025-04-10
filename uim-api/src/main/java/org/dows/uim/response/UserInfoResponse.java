package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserInfoResponse {
    @Schema(description = "用户实例Id")
    private Long userInstanceId;
}
