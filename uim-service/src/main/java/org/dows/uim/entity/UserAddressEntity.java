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
 * 用户地址表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_address")
public class UserAddressEntity extends Model<UserAddressEntity> {

    /**
     * 用户-地址维度ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userAddressId;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 用户实例ID
     */
    @Column(value = "user_instance_id", comment = "用户实例ID")
    private Long userInstanceId;

    /**
     * 国家编号
     */
    @Column(value = "country_no", comment = "国家编号")
    private String countryNo;

    /**
     * 国家
     */
    @Column(value = "country_name", comment = "国家")
    private String countryName;

    /**
     * 国家简称
     */
    @Column(value = "country_code", comment = "国家简称")
    private String countryCode;

    /**
     * 省编号
     */
    @Column(value = "province_no", comment = "省编号")
    private String provinceNo;

    /**
     * 省名称
     */
    @Column(value = "province_name", comment = "省名称")
    private String provinceName;

    /**
     * 省简称
     */
    @Column(value = "province_code", comment = "省简称")
    private String provinceCode;

    /**
     * 城市编号
     */
    @Column(value = "city_no", comment = "城市编号")
    private String cityNo;

    /**
     * 城市名
     */
    @Column(value = "city_name", comment = "城市名")
    private String cityName;

    /**
     * 市简称
     */
    @Column(value = "city_code", comment = "市简称")
    private String cityCode;

    /**
     * 详细地址
     */
    @Column(value = "address", comment = "详细地址")
    private String address;

    /**
     * 街道编码
     */
    @Column(value = "street_no", comment = "街道编码")
    private String streetNo;

    /**
     * 街道名称
     */
    @Column(value = "street_name", comment = "街道名称")
    private String streetName;

    /**
     * 区县编码
     */
    @Column(value = "district_no", comment = "区县编码")
    private String districtNo;

    /**
     * 区县名称
     */
    @Column(value = "district_name", comment = "区县名称")
    private String districtName;

    /**
     * 邮编
     */
    @Column(value = "zip_code", comment = "邮编")
    private String zipCode;

    /**
     * 业务线
     */
    @Column(value = "bizline", comment = "业务线")
    private String bizline;

    /**
     * 地址类型
     */
    @Column(value = "typ", comment = "地址类型")
    private Integer typ;

    /**
     * 状态
     */
    @Column(value = "state", comment = "状态")
    private Integer state;

    /**
     * 乐观锁, 默认: 0
     */
    @Column(value = "ver", comment = "乐观锁, 默认: 0")
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
