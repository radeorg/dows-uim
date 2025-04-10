package org.dows.uim.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class JobIndicatorResponse {
    @Parameter(description = "岗位指标列表")
    private List<OrgIndicatorResponse> indicatorList;
}
