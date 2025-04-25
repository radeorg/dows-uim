package org.dows.uim.response;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "岗位列表对象")
public class OrgJdPageResponse {
    @Schema(description = "JD列表")
    private Page<OrgJobJDDetailResponse> jdList;
}
