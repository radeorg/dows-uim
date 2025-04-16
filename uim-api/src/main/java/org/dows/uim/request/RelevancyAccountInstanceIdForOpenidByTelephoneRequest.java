package org.dows.uim.request;

import lombok.Data;
import org.dows.rade.constant.IdentifierType;

/**
 * 通过手机号关联账号实例ID
 *
 * @author lait.zhang@gmail.com
 * @description
 * @date 2022年11月23日 上午10:27:01
 */
@Data
public class RelevancyAccountInstanceIdForOpenidByTelephoneRequest {
    // 账号标识符ID
    private Long accountIdentifierId;
    //账号实例ID
    private Long accountInstanceId;
    // 账号标识
    private String identifier;
    // 账号标识符类型
    private IdentifierType identifierType;
}
