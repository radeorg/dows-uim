package org.dows.uim.request.JdKeyWord;

import lombok.Data;

import java.util.List;

@Data
public class BasicInfo {

    // 岗位名称，例如“Java高级开发工程师”
    private String jdName;

    // 性别要求，如“不限”“男”“女”
    private String genderRequirement;

    // 年龄范围要求，如“25-35岁”
    private String ageRange;

    // 工作经验要求，如“3年以上相关经验”
    private String experienceRequirement;

    // 学历要求，如“本科及以上”
    private String educationRequirement;

    // 招聘目的，如“扩充业务线”“替换人员”
    private List<String> recruitmentPurpose;

    // 需要解决的业务痛点，例如“流程效率低”“用户留存差”
    private String businessPainPoints;
}
