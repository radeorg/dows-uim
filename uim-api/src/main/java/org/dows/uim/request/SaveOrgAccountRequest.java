package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dows.uim.constant.AccountType;

@Schema(description = "添加组织账号")
@Data
public class SaveOrgAccountRequest {

    @Schema(description = "账号实例ID")
    private Long accountInstanceId;

    @Schema(description = "组织根ID")
    private Long orgRootId;
    @Schema(description = "组织树ID")
    private Long orgTreeId;
    @Schema(description = "组织名称")
    private String orgName;
    @Schema(description = "账号名称")
    private String nickname;
    @Schema(description = "账号类型[普通,招聘官,面试者]")
    private AccountType accountType;
    @Schema(description = "手机号")
    private String telephone;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "区号")
    private String zoneNo;
    @Schema(description = "应用ID")
    private String appId;
}
