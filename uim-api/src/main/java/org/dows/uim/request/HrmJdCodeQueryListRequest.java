package org.dows.uim.request;

import com.mybatisflex.annotation.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "岗位码值查询对象")
public class HrmJdCodeQueryListRequest {

    /**
     * 码编号
     */
    @Schema(description = "码编号")
    private List<Integer> codes;

    /**
     * 码值
     */
    @Schema(description = "码值")
    private List<String> values;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String codeType;


}
