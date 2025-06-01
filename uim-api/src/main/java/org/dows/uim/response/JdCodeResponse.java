package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName JdCodeDTO
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/1 16:31
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "码表查询结果")
public class JdCodeResponse {
    @Schema(description = "码编号")
    private Integer code;

    @Schema(description = "码值")
    private String value;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String codeType;
}
