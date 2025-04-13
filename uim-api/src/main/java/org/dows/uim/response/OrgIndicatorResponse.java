package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dows.uim.request.OrgIndicatorSaveRequest;

/**
 * 岗位指标对象。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Schema(name = "岗位指标对象")
public class OrgIndicatorResponse extends OrgIndicatorSaveRequest {


}
