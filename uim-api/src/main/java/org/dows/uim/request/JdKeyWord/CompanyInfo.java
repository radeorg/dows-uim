package org.dows.uim.request.JdKeyWord;

import lombok.Data;

/**
 * @ClassName 企业情况
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/1 21:17
 */


@Data
public class CompanyInfo {

    private Long hrmEnterpriseSituationId;

    // 公司规模，如“100-500人”
    private String scale;

    // 公司地址
    private String orgAddress;

    // 融资阶段，如“A轮”“B轮”“上市公司”
    private String fundingStage;

    // 项目类型，如“B端系统”“C端产品”
     private String projectType;

    // 项目当前进展，如“已上线”“开发中”
    private String projectProgress;

    // 相似岗位参考，如“腾讯-后台开发岗”
    private String similarPositions;
}
