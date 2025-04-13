package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "岗位要求")
public class OrgJdRequirements {
    @Schema(description = "HR名称")
    private String hrName;

    @Schema(description = "招聘官账号实例ID")
    private Long hrAccountInstanceId;

    @Schema(description = "最小年龄")
    private Integer minAge;

    @Schema(description = "最大年龄")
    private Integer maxAge;

    @Schema(description = "学历要求")
    private String educationRequirement;

    @Schema(description = "工作年限")
    private String workRequirement;

    @Schema(description = "专业技能")
    private String skillsRequirement;

    @Schema(description = "语言要求")
    private String languageRequirement;

    @Schema(description = "性别要求")
    private String genderRequirement;

    @Schema(description = "其他要求")
    private String otherRequirement;
}
