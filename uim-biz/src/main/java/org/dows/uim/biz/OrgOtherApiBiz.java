package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.update.UpdateChain;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.constant.CommonDelEnum;
import org.dows.uim.entity.OrgAddressEntity;
import org.dows.uim.entity.OrgJdCategoryEntity;
import org.dows.uim.request.OrgAddressRequest;
import org.dows.uim.response.OrgAddressResponse;
import org.dows.uim.response.OrgJdcategoryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrgOtherApiBiz {
    @Operation(summary = "获取地址列表")
    public List<OrgAddressResponse> getOrgAddressList(Long orgRootId) throws UnavailableException {
        List<OrgAddressResponse> responses = new ArrayList<>();

        if(Objects.isNull(orgRootId)){
            throw new UnavailableException("orgRootId 必填");
        }

        List<OrgAddressEntity> orgAddressEntities = QueryChain.of(OrgAddressEntity.class)
                .eq(OrgAddressEntity::getOrgRootId, orgRootId, Objects.nonNull(orgRootId))
                .eq(OrgAddressEntity::getDeleted, CommonDelEnum.NORMAL.getCode()).list();
        for(OrgAddressEntity item : orgAddressEntities){
            OrgAddressResponse itemResponse = new OrgAddressResponse();
            BeanUtils.copyProperties(item, itemResponse);
            responses.add(itemResponse);
        }

        return responses;
    }

    @Operation(summary = "保存地址信息")
    public OrgAddressResponse saveOrgAddress(OrgAddressRequest orgAddressRequest) throws UnavailableException {
        OrgAddressEntity objEntity = new OrgAddressEntity();

        if(Objects.isNull(orgAddressRequest.getOrgRootId())){
            throw new UnavailableException("orgRootId 必填");
        }

        BeanUtils.copyProperties(orgAddressRequest, objEntity, OrgAddressEntity.class);
        objEntity.setTs(new Date());
        objEntity.setState(1);
        objEntity.setDeleted(CommonDelEnum.NORMAL.getCode());

        objEntity.saveOrUpdate();
        orgAddressRequest.setOrgAddressId(objEntity.getOrgAddressId());

        return BeanUtil.copyProperties(orgAddressRequest, OrgAddressResponse.class);
    }

    @Operation(summary = "删除某个地址")
    public Boolean deleteOrgAddress(Long orgAddressId) throws UnavailableException {
        if(Objects.isNull(orgAddressId)){
            throw new UnavailableException("orgAddressId 必填");
        }

        boolean updateRec = UpdateChain.of(OrgAddressEntity.class)
                .set(OrgAddressEntity::getDeleted, CommonDelEnum.DELETE.getCode())
                .eq(OrgAddressEntity::getOrgAddressId, orgAddressId, Objects.nonNull(orgAddressId)).update();

        return updateRec;
    }

    @Operation(summary = "获取职位分类列表")
    public List<OrgJdcategoryResponse> getOrgJdcategoryList(Long orgRootId) throws UnavailableException {
        List<OrgJdcategoryResponse> responses = new ArrayList<>();

        if(Objects.isNull(orgRootId)){
            throw new UnavailableException("orgRootId 必填");
        }

        List<OrgJdCategoryEntity> orgJdCategoryEntityList = QueryChain.of(OrgJdCategoryEntity.class)
                .eq(OrgJdCategoryEntity::getOrgTreeId, orgRootId, Objects.nonNull(orgRootId)).list();
        for(OrgJdCategoryEntity item : orgJdCategoryEntityList){
            OrgJdcategoryResponse itemResponse = new OrgJdcategoryResponse();
            BeanUtils.copyProperties(item, itemResponse);
            responses.add(itemResponse);
        }

        return responses;
    }
}
