package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "岗位指标对象")
public class OrgIndicatorListSaveRequest {
    @Schema(description = "岗位指标列表")
    private List<OrgIndicatorSaveRequest> indicatorList;
}
