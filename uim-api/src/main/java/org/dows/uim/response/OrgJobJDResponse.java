package org.dows.uim.response;

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

    @Schema(description = "组织树rootID")
    private Long orgRootId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "岗位规则ID")
    private Long orgRuleId;

    @Schema(description = "岗位地址")
    private String orgAddress;

    @Schema(description = "人事账号实例ID")
    private Long ownerId;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "岗位名称+岗位编号")
    private String jdName;

    @Schema(description = "岗位描述")
    private String description;

    @Schema(description = "状态[1:下架，2:上架]")
    private Integer state;

    @Schema(description = "发布渠道集合")
    private String channels;

    @Schema(description = "应用ID")
    private String appId;

    @Schema(description = "时间戳")
    private Date ts;

}
