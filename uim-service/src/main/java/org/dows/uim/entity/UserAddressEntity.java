package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.BaseEntity;
import org.dows.uim.AutoFillDataListener;

import java.util.Date;

/**
 * 用户地址表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户地址表")
@Table(value = "user_address", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class UserAddressEntity extends BaseEntity<UserAddressEntity> {

    /**
     * 用户-地址维度ID
     */
    @Schema(description = "用户-地址维度ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long userAddressId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 用户实例ID
     */
    @Schema(description = "用户实例ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 国家编号
     */
    @Schema(description = "国家编号")
    @Column(value = "country_no")
    private String countryNo;

    /**
     * 国家
     */
    @Schema(description = "国家")
    @Column(value = "country_name")
    private String countryName;

    /**
     * 国家简称
     */
    @Schema(description = "国家简称")
    @Column(value = "country_code")
    private String countryCode;

    /**
     * 省编号
     */
    @Schema(description = "省编号")
    @Column(value = "province_no")
    private String provinceNo;

    /**
     * 省名称
     */
    @Schema(description = "省名称")
    @Column(value = "province_name")
    private String provinceName;

    /**
     * 省简称
     */
    @Schema(description = "省简称")
    @Column(value = "province_code")
    private String provinceCode;

    /**
     * 城市编号
     */
    @Schema(description = "城市编号")
    @Column(value = "city_no")
    private String cityNo;

    /**
     * 城市名
     */
    @Schema(description = "城市名")
    @Column(value = "city_name")
    private String cityName;

    /**
     * 市简称
     */
    @Schema(description = "市简称")
    @Column(value = "city_code")
    private String cityCode;

    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    @Column(value = "address")
    private String address;

    /**
     * 街道编码
     */
    @Schema(description = "街道编码")
    @Column(value = "street_no")
    private String streetNo;

    /**
     * 街道名称
     */
    @Schema(description = "街道名称")
    @Column(value = "street_name")
    private String streetName;

    /**
     * 区县编码
     */
    @Schema(description = "区县编码")
    @Column(value = "district_no")
    private String districtNo;

    /**
     * 区县名称
     */
    @Schema(description = "区县名称")
    @Column(value = "district_name")
    private String districtName;

    /**
     * 邮编
     */
    @Schema(description = "邮编")
    @Column(value = "zip_code")
    private String zipCode;

    /**
     * 业务线
     */
    @Schema(description = "业务线")
    @Column(value = "bizline")
    private String bizline;

    /**
     * 地址类型
     */
    @Schema(description = "地址类型")
    @Column(value = "typ")
    private Integer typ;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Column(value = "state")
    private Integer state;

    /**
     * 乐观锁，默认为0
     */
    @Schema(description = "乐观锁，默认为0")
    @Column(value = "ver", onUpdateValue = "ver+1")
    private Integer ver;

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
