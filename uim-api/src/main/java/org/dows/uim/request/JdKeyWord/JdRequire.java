package org.dows.uim.request.JdKeyWord;

import lombok.Data;

/**
 * @ClassName JD要求
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/1 21:07
 */


@Data
public class JdRequire {



    // 汇报对象职位或名称，如“CTO”“技术总监”
    private String reportTo;

    // 是否为管理岗，true 表示需要带团队
    private String teamLead;

    // 管辖下属人数
    private String subordinateCount;

    // 与其他部门/团队的协作频率，如“日常协作”“定期评审”
    private String collaborationFrequency;

    // 是否需要编写技术文档
    private String documentRequirement;

    // 所需编写的文档类型，例如“设计文档”“API 文档”
    private String documentTypes;

    // 技术栈列表，例如“Java”“SpringBoot”“MySQL”
    private String techStack;

    // 所需专业证书，如 PMP、软考等
    private String certifications;

    // 语言要求，例如“英语读写能力”“CET-6”
    private String languageRequirements;

}
