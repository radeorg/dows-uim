package org.dows.uim.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.Date;

@Data
public class JobSearchResponse {
    @Parameter(description = "岗位描述ID")
    private Long orgJdId;

    @Parameter(description = "岗位描述")
    private String description;

    @Parameter(description = "时间戳")
    private Date ts;
}
