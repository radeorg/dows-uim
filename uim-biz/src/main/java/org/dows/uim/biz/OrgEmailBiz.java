package org.dows.uim.biz;


import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.aac.AacContext;
import org.dows.rade.aac.AacUser;
import org.dows.uim.entity.OrgEmailEntity;
import org.dows.uim.response.OrgEmailResponse;
import org.dows.uim.service.OrgEmailService;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrgEmailBiz
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/4/24 20:39
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class OrgEmailBiz {
    private final AacContext aacContext;
    private final OrgEmailService emailService;

    public OrgEmailResponse getOrgEmailInfo(){

        AacUser aacUser = aacContext.getAacUser();
        Long rootId = aacUser.getOrgRootId();
        OrgEmailEntity emailEntity = emailService.getOne(QueryWrapper.create()
                .eq(OrgEmailEntity::getOrgRootId,rootId)
                .eq(OrgEmailEntity::getDeleted,0)
        );
        return BeanUtil.toBean(emailEntity, OrgEmailResponse.class);
    }

}
