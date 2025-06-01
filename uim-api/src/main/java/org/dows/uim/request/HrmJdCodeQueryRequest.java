package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "岗位码值查询对象")
public class HrmJdCodeQueryRequest {

    /**
     * 码值
     */
    @Schema(description = "码值")
    private String value;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String codeType;


}
