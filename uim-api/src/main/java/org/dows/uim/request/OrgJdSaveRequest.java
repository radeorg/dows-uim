package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dows.uim.response.OrgJobJDDetailResponse;

@Data
@Schema(name = "岗位信息对象")
public class OrgJdSaveRequest extends OrgJobJDDetailResponse {
}
