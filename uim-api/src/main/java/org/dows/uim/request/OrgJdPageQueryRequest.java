package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "岗位查询对象")
public class OrgJdPageQueryRequest {

    @Schema(description = "岗位描述ID")
    private Long orgJdId;

    @Schema(description = "组织树rootID")
    private Long orgRootId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "岗位名称+岗位编号")
    private String jdName;

    @Schema(description = "发布开始日期")
    private String startDate;

    @Schema(description = "发布结束日期")
    private String endDate;;

    @Schema(description = "分页大小")
    private Integer pageSize;

    @Schema(description = "当前页数")
    private Integer pageNum;
}
