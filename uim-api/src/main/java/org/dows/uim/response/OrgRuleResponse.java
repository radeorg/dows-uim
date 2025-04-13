package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dows.uim.request.OrgRuleSaveRequest;

@Data
@Schema(name = "岗位规则对象")
public class OrgRuleResponse extends OrgRuleSaveRequest {
}
