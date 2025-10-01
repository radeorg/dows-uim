package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName EmailStatusResponse
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/10/1 22:22
 */

@Data
@Builder
@Schema(name = "邮箱状态")
public class EmailStatusResponse {
    /**
     * 组织邮箱ID
     */
    @Schema(description = "邮箱绑定状态")
    private Boolean boundStatus;

    /**
     * 企业邮箱
     */
    @Schema(description = "邮箱连接状态")
    private Boolean connectStatus;
}
