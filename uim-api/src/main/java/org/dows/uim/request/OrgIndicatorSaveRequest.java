package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "岗位指标对象")
public class OrgIndicatorSaveRequest {
    @Schema(description = "岗位指标ID")
    private Long orgIndicatorId;

    @Schema(description = "岗位行动ID")
    private Long orgActionId;

    @Schema(description = "岗位规则ID")
    private Long orgRuleId;

    @Schema(description = "指标名称")
    private String indicatorName;

    @Schema(description = "指标英文名")
    private String indicatorCode;

    @Schema(description = "指标关键字")
    private String indicatorKeyword;

    @Schema(description = "指标别名")
    private String indicatorAlias;

    @Schema(description = "指标别名1")
    private String indicatorAlias1;

    @Schema(description = "数据类型")
    private String dataType;

    @Schema(description = "逻辑表达式>,<,=，equals,contain")
    private String condition;

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
