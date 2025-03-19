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
import java.lang.Integer;

/**
 * 用户实例表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RequiredArgsConstructor
@Data(staticConstructor = "create")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户实例表")
@Table(value = "user_instance")
public class UserInstanceEntity extends Model<UserInstanceEntity> {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @Id(keyType = KeyType.Auto)
    private Long userInstanceId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Column(value = "user_name")
    private String userName;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    @Column(value = "identify_no")
    private String identifyNo;

    /**
     * 用户年龄
     */
    @Schema(description = "用户年龄")
    @Column(value = "user_age")
    private Integer userAge;

    /**
     * 性别
     */
    @Schema(description = "性别")
    @Column(value = "sex")
    private Integer sex;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 乐观锁, 默认: 0
     */
    @Schema(description = "乐观锁, 默认: 0")
    @Column(value = "ver")
    private Integer ver;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;


}
