package org.dows.uim.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 岗位指标表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data

@Schema(name = "岗位指标表")
public class OrgIndicatorResponse {

    @Schema(description = "岗位指标ID")
    private Long orgIndicatorId;

    @Schema(description = "岗位行动ID")
    private Long orgActionId;

    @Schema(description = "指标名称")
    private String indicatorName;

    @Schema(description = "指标关键字")
    private String indicatorKeyword;

    @Schema(description = "指标分值")
    private Integer indicatorScore;

    @Schema(description = "指标匹配度")
    private Integer matchScore;

    @Schema(description = "是否可用0-可用，1-不可用")
    private Integer enabled;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "应用ID")
    private String appId;

    @Schema(description = "时间戳")
    private Date ts;

}
