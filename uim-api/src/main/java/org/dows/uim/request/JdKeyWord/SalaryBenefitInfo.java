package org.dows.uim.request.JdKeyWord;

import lombok.Data;

import java.util.List;

/**
 * @ClassName 福利特色
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/1 21:21
 */

@Data
public class SalaryBenefitInfo {

    private Long hrmFeatureBenefitsId;

    // 月薪范围，例如“15k-25k”
    private String monthlySalaryRange;

    // 工作模式，例如“远程办公”“混合办公”“坐班”
    private String workMode;

    // 核心福利项，如“五险一金”“弹性上下班”“年终奖”
    private List<String> coreBenefits;

    // 团队特点，如“扁平化管理”“技术氛围浓厚”
    private List<String> teamFeatures;

}
