package org.dows.uim.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.mybatisflex.core.activerecord.Model;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 组织树表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "org_tree")
public class OrgTreeEntity extends Model<OrgTreeEntity> {

    /**
     * 组织树ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long orgTreeId;

    /**
     * 父ID
     */
    @Column(value = "pid", comment = "父ID")
    private Long pid;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 组织名
     */
    @Column(value = "org_name", comment = "组织名")
    private String orgName;

    /**
     * 组织码
     */
    @Column(value = "org_code", comment = "组织码")
    private String orgCode;

    /**
     * 组织头像
     */
    @Column(value = "org_avator", comment = "组织头像")
    private String orgAvator;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

    /**
     * 版本
     */
    @Column(value = "ver", comment = "版本")
    private Integer ver;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Column(value = "deleted", comment = "逻辑删除  0未删除  1 删除")
    private Integer deleted;

    /**
     * 时间戳
     */
    @Column(value = "ts", comment = "时间戳")
    private Date ts;


}
