package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * 职位类别表(OrgJdCategory)实体类
 *
 * @since 2025-04-22 11:31:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织JD类目")
@Table(value = "org_jd_category")
public class OrgJdCategoryEntity extends BaseEntity<OrgJdCategoryEntity> {
    /**
     * 职位类别ID
     */
    @Schema(description = "职位类别ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgJdCategoryId;
    /**
     * 组织树ID
     */
    private Long orgTreeId;
    /**
     * 名称
     */
    private String categoryName;
    /**
     * 操作者ID
     */
    private Long operatorId;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 时间戳
     */
    private Date ts;
    /**
     * 更新时间
     */
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;

}

