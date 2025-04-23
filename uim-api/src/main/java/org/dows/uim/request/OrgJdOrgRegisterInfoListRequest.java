package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "岗位、组织注册信息查询对象")
public class OrgJdOrgRegisterInfoListRequest {

    @Schema(description = "岗位描述ID集合")
    private List<Long> orgJdIds;

    @Schema(description = "是否查询企业注册信息")
    private Boolean queryOrgRegisterInfo;

}
