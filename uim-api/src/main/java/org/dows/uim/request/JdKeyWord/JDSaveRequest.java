package org.dows.uim.request.JdKeyWord;

import lombok.Data;

import java.util.List;

/**
 * @ClassName JDResponse
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/1 21:47
 */

@Data
public class JDSaveRequest {

    private Long orgjdId;

    // 关联hr
    private Long ownerId;

    // 职位类别
    private Long orgJdCategoryId;

    // 岗位编码，用于内部标识
    private String jdNo;

    //  基本信息
    private BasicInfo basicInfo;

    // 公司信息对象
    private CompanyInfo companyInfo;

    private JdRequire jdRequire;

    // 其他附加要求
    private String otherRequirements;

    // 薪资与福利信息对象
    private SalaryBenefitInfo salaryBenefitInfo;

    // 岗位标签（关键词提取）
    private List<String> jobTags;


    // 职位描述
    private String requirements;

    // JD 风险提示，如“薪资偏低”“性别歧视表述”等
    private String risks;

    // 推荐薪资范围
    private String salarySuggestion;
}
