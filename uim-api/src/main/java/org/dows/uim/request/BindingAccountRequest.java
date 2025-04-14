package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "Binding Account Request")
@Data
public class BindingAccountRequest {
    private Long accountInstanceId;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "手机号")
    private String telephone;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "区号")
    private String zoneNo;
    @Schema(description = "用户来源")
    private String source;
    @Schema(description = "推荐人")
    private String referralsNo;
    // 是否校验字段值是否存在[telephone, email...],在该对象中存在的字段名
    @Schema(description = "是否校验字段值是否存在[telephone, email...],请填写在该对象中存在的字段名")
    private List<String> verifiers;
}
