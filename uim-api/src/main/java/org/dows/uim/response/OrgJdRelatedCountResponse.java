package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Jd统计")
public class OrgJdRelatedCountResponse {

    @Schema(description = "已安排面试次数")
    private Integer interviewedCount = 0;

    @Schema(description = "收到简历量份数")
    private Integer resumeCount = 0;

    @Schema(description = "精确匹配量份数")
    private Integer matchCount = 0;
}
