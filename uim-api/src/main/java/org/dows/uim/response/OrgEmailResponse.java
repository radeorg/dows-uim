package org.dows.uim.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName OrgEmailResponse
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/4/24 20:32
 */

@Data
@Schema(name = "企业主油箱")
public class OrgEmailResponse {


    /**
     * 企业邮箱
     */
    @Schema(description = "企业邮箱")
    private String email;


    @Schema(description = "授权码")
    private String authCode;

    /**
     * JSON配置
     */
    @Schema(description = "JSON配置")
    private String configJson;
}
