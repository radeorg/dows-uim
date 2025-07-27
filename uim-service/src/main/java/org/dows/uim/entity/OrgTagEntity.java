package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.BaseEntity;
import org.dows.rade.crud.AutoFillDataListener;

import java.util.Date;

/**
 * 组织标签表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织标签表")
@Table(value = "org_tag", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgTagEntity extends BaseEntity<OrgTagEntity> {

    /**
     * 组织标签ID
     */
    @Schema(description = "组织标签ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgTagId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 标签名
     */
    @Schema(description = "标签名")
    @Column(value = "tag_name")
    private String tagName;

    /**
     * 标签颜色
     */
    @Schema(description = "标签颜色")
    @Column(value = "tag_color")
    private String tagColor;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id", tenantId = true)
    private String appId;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}
