package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.dows.rade.constant.IdentifierType;

import java.util.Date;

@Data
public class HrAccountInstanceResponse {
    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    private Long accountInstanceId;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;


    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String telephone;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;


    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    private Long operatorId;

    /**
     * 乐观锁，默认为0
     */
    @Schema(description = "乐观锁，默认为0")
    private Integer ver;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    private Date ts;

    private Date ut;
    /**
     * 组织名/部门名
     */
    @Schema(description = "部门名")
    private String orgName;
    /**
     * 已安排面试
     */
    @Schema(description = "已安排面试")
    private Long resumeCount;
    /**
     * 成功录用人数
     */
    @Schema(description = "成功录用人数")
    private Long interviewCount;

}
