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

    @Schema(description = "岗位名称")
    private String jdName;

    @Schema(description = "招聘官id")
    private Long ownerId;

    @Schema(description = "岗位标签内容")
    private String tagContent;

    @Schema(description = "工作经验 0\"不限\", 1\"应届\",2 \"1-3年\",3 \"3-5年\", 4\"5-10年\",5 \"10年以上\"")
    private Integer workExper;
}
