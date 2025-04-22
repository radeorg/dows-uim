package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class OrgJdcategoryResponse {

    @Schema(description = "职位类别ID")
    private Long orgJdCategoryId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "职位类别名称")
    private String categoryName;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "AppId")
    private String appId;

    @Schema(description = "操作时间")
    private Date ts;

    @Schema(description = "更新时间")
    private Date ut;

    @Schema(description = "归属Id")
    private Long ownerId;
}
