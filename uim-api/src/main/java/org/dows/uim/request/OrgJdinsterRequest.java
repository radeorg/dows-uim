package org.dows.uim.request;

import com.mybatisflex.annotation.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *@ClassName OrgJdinsterRequest
 *@Description TODO
 *@Author jack.china.ye
 *@Date 2025/6/1 14:54
 */

@Data
@Schema(name = "岗位JD")
public class OrgJdinsterRequest  {





    @Schema(description = "岗位地址")
    @Column(value = "org_address")
    private String orgAddress;




    /**
     * 岗位名称+岗位编号
     */
    @Schema(description = "岗位名称+岗位编号")
    @Column(value = "jd_name")
    private String jdName;

    /**
     * 性别要求
     */
    @Schema(description = "性别要求 0：不限1:男；2：女；3：优先男性；4：优先女性")
    private Integer gender;

    /**
     * 年龄范围
     */
    @Schema(description = "年龄范围 0：\"不限\",1： \"25-30岁\",2： \"30-35岁\",3： \"35-40岁\", 4：\"40岁以上\"")
    private Integer ageRange;

    /**
     * 工作经验
     */
    @Schema(description = "工作经验 0\"不限\", 1\"应届\",2 \"1-3年\",3 \"3-5年\", 4\"5-10年\",5 \"10年以上\"")
    private Integer workExper;

    /**
     * 最低学历
     */
    @Schema(description = "最低学历 0\"不限\",1 \"大专及以上\", 2\"本科及以上\",3 \"硕士及以上\", 4\"博士及以上\"")
    private Integer minEducation;


    /**
     * 其他要求
     */
    @Schema(description = "其他要求")
    private String otherRequire;


    /**
     * 岗位描述
     */
    @Schema(description = "岗位描述")
    private String description;

    @Schema(description = "招聘目的")
    private String recruitmentPurpose;

    @Schema(description = "企业情况id")
    private Long enterpriseSituationId;

    @Schema(description = "福利特色id")
    private Long hrmFeatureBenefitsId;


    /**
     * 发布渠道集合
     */
    @Schema(description = "发布渠道集合")
    private String channels;



}
