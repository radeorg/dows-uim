package org.dows.uim.mapper;

import org.dows.uim.entity.AccountIdentifierEntity;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号标识表 映射层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Mapper
public interface AccountIdentifierMapper extends BaseMapper<AccountIdentifierEntity> {

    void dd();
}
