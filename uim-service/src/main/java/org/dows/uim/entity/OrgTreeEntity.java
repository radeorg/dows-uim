package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * 组织树表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织树表")
@Table(value = "org_tree")
public class OrgTreeEntity extends BaseEntity<OrgTreeEntity> {

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Id(keyType = KeyType.Auto)
    private Long orgTreeId;

    /**
     * 父ID
     */
    @Schema(description = "父ID")
    @Column(value = "pid")
    private Long pid;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 组织名
     */
    @Schema(description = "组织名")
    @Column(value = "org_name")
    private String orgName;

    /**
     * 组织码
     */
    @Schema(description = "组织码")
    @Column(value = "org_code")
    private String orgCode;

    /**
     * 组织头像
     */
    @Schema(description = "组织头像")
    @Column(value = "org_avator")
    private String orgAvator;

    /**
     * ID路径
     */
    @Schema(description = "ID路径")
    @Column(value = "id_path")
    private String idPath;

    /**
     * 名称路径
     */
    @Schema(description = "名称路径")
    @Column(value = "name_path")
    private String namePath;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 版本
     */
    @Schema(description = "版本")
    @Column(value = "ver")
    private Integer ver;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Schema(description = "逻辑删除  0未删除  1 删除")
    @Column(value = "deleted")
    private Integer deleted;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;


}
