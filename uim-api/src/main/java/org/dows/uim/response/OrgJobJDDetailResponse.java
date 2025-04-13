package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dows.uim.request.OrgJdRequirements;

/**
 * 岗位JD详细对象。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Schema(name = "岗位JD详细对象")
public class OrgJobJDDetailResponse extends OrgJobJDResponse{
    @Schema(description = "岗位要求")
    private OrgJdRequirements orgJdRequirements;
}
