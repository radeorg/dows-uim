package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.constant.IdentifierType;
import org.dows.uim.api.AccountTypeRequest;
import org.dows.uim.api.AccountTypeResponse;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.entity.AccountRoleEntity;
import org.dows.uim.entity.AccountTypeEntity;
import org.dows.uim.exception.UimException;
import org.dows.uim.handler.AccountHandler;
import org.dows.uim.handler.HrAccountHandler;
import org.dows.uim.request.FindAccountIdentifierRequest;
import org.dows.uim.request.*;
import org.dows.uim.response.*;
import org.dows.uim.service.AccountIdentifierService;
import org.dows.uim.service.AccountInstanceService;
import org.dows.uim.service.AccountTypeService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class HrAccountApiBiz {
    private final HrAccountHandler hrAccountHandler;

    public Page<HrAccountInstanceResponse> page(HrAccountInstanceRequest request) {
        return hrAccountHandler.page(request);
    }
    public Boolean delete(Long accountInstanceId) {
        return hrAccountHandler.delete(accountInstanceId);
    }
}
