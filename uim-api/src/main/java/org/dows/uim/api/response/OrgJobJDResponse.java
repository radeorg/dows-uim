package org.dows.uim.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 岗位JD表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Schema(name = "岗位JD表")
public class OrgJobJDResponse  {

    @Schema(description = "岗位描述ID")
    private Long orgJdId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "岗位描述")
    private String description;

    @Schema(description = "发布渠道集合")
    private String channels;

    @Schema(description = "应用ID")
    private String appId;

    @Schema(description = "时间戳")
    private Date ts;

}
