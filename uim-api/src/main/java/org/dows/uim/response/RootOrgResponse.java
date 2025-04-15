package org.dows.uim.response;

import lombok.Data;

@Data
public class RootOrgResponse {
    // 账户实例ID
    private Long accountInstanceId;
    // 组织根节点ID
    private Long rootOrgId;
    // 组织树ID
    private Long orgTreeId;
    // 组织名称
    private String aliasName;
    // 默认组织
    private boolean defaultOrg;

}
