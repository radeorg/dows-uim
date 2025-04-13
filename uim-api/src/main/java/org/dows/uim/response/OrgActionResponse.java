package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dows.uim.request.OrgActionSaveRequest;

@Data
@Schema(name = "岗位动作对象")
public class OrgActionResponse extends OrgActionSaveRequest {
}
