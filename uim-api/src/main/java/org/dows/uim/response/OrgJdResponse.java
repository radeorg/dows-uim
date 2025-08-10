package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 岗位JD表 实体类。
 */
@Data
@Schema(name = "岗位JD表")
public class OrgJdResponse {

    @Schema(description = "岗位描述ID")
    private Long orgJdId;

    @Schema(description = "组织树rootID")
    private Long orgRootId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "岗位编号")
    private String jdNo;

    @Schema(description = "岗位名称+岗位编号")
    private String jdName;

    @Schema(description = "招聘官id")
    private Long ownerId;

    @Schema(description = "岗位标签内容")
    private String tagContent;
}
