package org.dows.uim.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.mybatisflex.core.activerecord.Model;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.lang.Long;
import java.util.Date;
import java.lang.String;

/**
 * 岗位JD表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RequiredArgsConstructor
@Data(staticConstructor = "create")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位JD表")
@Table(value = "org_jd")
public class OrgJdEntity extends Model<OrgJdEntity> {

    /**
     * 岗位描述ID
     */
    @Schema(description = "岗位描述ID")
    @Id(keyType = KeyType.Auto)
    private Long orgJdId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 岗位描述
     */
    @Schema(description = "岗位描述")
    @Column(value = "description")
    private String description;

    /**
     * 发布渠道集合
     */
    @Schema(description = "发布渠道集合")
    @Column(value = "channels")
    private String channels;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;


}
