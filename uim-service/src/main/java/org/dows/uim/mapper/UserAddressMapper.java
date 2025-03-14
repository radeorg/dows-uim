package org.dows.uim.mapper;

import org.dows.uim.entity.UserAddressEntity;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户地址表 映射层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddressEntity> {


}
