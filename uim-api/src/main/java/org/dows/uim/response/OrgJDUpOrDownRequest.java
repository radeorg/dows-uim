package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 岗位JD表 上架下架。
 *
 */
@Data
@Schema(name = "岗位上架下架")
public class OrgJDUpOrDownRequest {

    @Schema(description = "岗位描述ID")
    private Long orgJdId;

    @Schema(description = "状态[1:下架，2:上架]")
    private Integer state;

}
