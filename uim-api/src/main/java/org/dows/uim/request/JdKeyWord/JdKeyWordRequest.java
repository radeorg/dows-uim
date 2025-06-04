package org.dows.uim.request.JdKeyWord;

import lombok.Data;

/**
 * @ClassName JdKeyWordRequest
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/1 21:23
 */

@Data
public class JdKeyWordRequest {


    //  基本信息
    private BasicInfo basicInfo;

    // 公司信息对象
    private CompanyInfo companyInfo;

    private JdRequire jdRequire;

    // 薪资与福利信息对象
    private SalaryBenefitInfo salaryBenefitInfo;

    // 其他附加要求
    private String otherRequirements;
}
