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
 * 账号推荐人表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "账号推荐人表")
@Table(value = "account_relation")
public class AccountRelationEntity extends BaseEntity<AccountRelationEntity> {

    /**
     * 账号关系ID
     */
    @Schema(description = "账号关系ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long accountRelationId;

    /**
     * 父节点ID
     */
    @Schema(description = "父节点ID")
    @Column(value = "pid")
    private Long pid;

    /**
     * 账号推荐人ID
     */
    @Schema(description = "账号推荐人ID")
    @Column(value = "account_referrer_id")
    private Long accountReferrerId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    @Column(value = "account_instance_id")
    private Long accountInstanceId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 组
     */
    @Schema(description = "组")
    @Column(value = "group")
    private Integer group;

    /**
     * 深度
     */
    @Schema(description = "深度")
    @Column(value = "deep")
    private Integer deep;

    /**
     * 顺序
     */
    @Schema(description = "顺序")
    @Column(value = "seq")
    private Integer seq;

    /**
     * seq 向量
     */
    @Schema(description = "seq 向量")
    @Column(value = "vectors")
    private String vectors;

    /**
     * 父节点顺序
     */
    @Schema(description = "父节点顺序")
    @Column(value = "id_path")
    private String idPath;

    /**
     * 乐观锁，默认为0
     */
    @Schema(description = "乐观锁，默认为0")
    @Column(value = "ver")
    private Integer ver;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted", isLogicDelete = true)
    private Integer deleted;

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
