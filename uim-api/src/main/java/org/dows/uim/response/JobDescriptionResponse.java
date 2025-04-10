package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class JobDescriptionResponse {
    @Schema(description = "岗位描述列表")
    private List<OrgJobJDResponse> jobList;
}
