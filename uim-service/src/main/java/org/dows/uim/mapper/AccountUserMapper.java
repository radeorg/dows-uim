package org.dows.uim.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dows.uim.entity.AccountUserEntity;

/**
 * 账号用户表 映射层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Mapper
public interface AccountUserMapper extends BaseMapper<AccountUserEntity> {


}
