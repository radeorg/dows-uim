package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "岗位列表对象")
public class OrgJdListResponse {
    @Schema(description = "JD列表")
    private List<OrgJobJDDetailResponse> jdList;
}
