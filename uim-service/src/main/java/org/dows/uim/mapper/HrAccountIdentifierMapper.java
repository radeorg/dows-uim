//package org.dows.uim.mapper;
//
//import com.mybatisflex.core.BaseMapper;
//import org.apache.ibatis.annotations.Mapper;
//import org.dows.uim.entity.AccountIdentifierEntity;
//
///**
// * 账号标识表 映射层。
// *
// * @author lait.zhang@gmail.com
// * @since 1.0
// */
//@Mapper
//public interface HrAccountIdentifierMapper {
//
//    /**
//     * 根据 accountInstanceId 统计记录数量
//     * @param accountInstanceId 账户实例 ID
//     * @return 符合条件的记录数量
//     */
//    @SQL("SELECT COUNT(*) FROM resume_instance WHERE 1 = 1" +
//            "${accountInstanceId != null ? ' AND account_instance_id = #{accountInstanceId}' : ''}")
//    long countByAccountInstanceId(Long accountInstanceId);
//}
