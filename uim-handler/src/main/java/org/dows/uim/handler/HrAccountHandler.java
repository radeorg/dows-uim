package org.dows.uim.handler;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.DbChain;
import com.mybatisflex.core.row.Row;
import com.mybatisflex.core.update.UpdateChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.constant.IdentifierType;
import org.dows.rade.status.CommonStatusCode;
import org.dows.uim.constant.AccountType;
import org.dows.uim.constant.CommonDelEnum;
import org.dows.uim.entity.*;
import org.dows.uim.exception.UimException;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.AddOrgAccountRequest;
import org.dows.uim.request.HrAccountInstanceRequest;
import org.dows.uim.response.HrAccountInstanceResponse;
import org.dows.uim.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class HrAccountHandler {

    private final AccountInstanceService accountInstanceService;

    public Page<HrAccountInstanceResponse> page(HrAccountInstanceRequest request) {

        // 创建分页对象
        Page<HrAccountInstanceResponse> page = new Page<>(
                Long.valueOf(request.getPageNum()),
                Long.valueOf(request.getPageSize())
        );
        Page<HrAccountInstanceResponse> resultPage = QueryChain.of(AccountInstanceEntity.class)
                .select(AccountInstanceEntity::getAccountInstanceId,AccountInstanceEntity::getNickname)
//                .innerJoin(AccountIdentifierEntity.class)
//                .on(AccountInstanceEntity::getAccountInstanceId, AccountIdentifierEntity::getAccountInstanceId)
                .innerJoin(AccountTypeEntity.class)
                .on(AccountInstanceEntity::getAccountInstanceId, AccountTypeEntity::getAccountInstanceId)
                .innerJoin(OrgNodeEntity.class)
                .on(OrgNodeEntity::getAccountInstanceId, AccountInstanceEntity::getAccountInstanceId)
                //todo 后期需要打开
//                .innerJoin(OrgTreeEntity.class)
//                .on(OrgTreeEntity::getId, OrgNodeEntity::getOrgTreeId)
                .select(OrgTreeEntity::getOrgName)
                .isNotNull(OrgNodeEntity::getOrgTreeId)
                .eq(AccountTypeEntity::getAccountType,AccountType.ORG_RECRUIT_ACCOUNT.getValue())
                .eq(AccountInstanceEntity::getAppId,request.getAppId(), Objects.nonNull(request.getAppId()))
                .eq(OrgNodeEntity::getOrgRootId,request.getOrgRootId(), Objects.nonNull(request.getOrgRootId()))
                .like(AccountInstanceEntity::getNickname,request.getNickname(), Objects.nonNull(request.getNickname()))
                .like(AccountInstanceEntity::getTelephone,request.getTelephone(), Objects.nonNull(request.getTelephone()))
                .like(OrgTreeEntity::getOrgName,request.getOrgName(), Objects.nonNull(request.getOrgName()))
                .eq(AccountInstanceEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .orderBy(AccountInstanceEntity::getTs, false)
                .pageAs(page,HrAccountInstanceResponse.class);

        if(resultPage == null || resultPage.getRecords().isEmpty()){
            return resultPage;
        }

        Set<Long> interviewInstanceIds = resultPage.getRecords().stream().map(HrAccountInstanceResponse::getAccountInstanceId).collect(Collectors.toSet());

        List<HrAccountInstanceResponse> resumeCountList = DbChain.table("interview_invite").select("interviewer_id as accountInstanceId",
                        "count(1) as resumeCount ")
                        .in("interviewer_id",interviewInstanceIds).groupBy("interviewer_id").listAs(HrAccountInstanceResponse.class);
        List<HrAccountInstanceResponse> interviewCountList = DbChain.table("interview_invite").select("interviewer_id as accountInstanceId",
                        "count(1) as interviewCount ")
                .in("interviewer_id",interviewInstanceIds)
                .set("passed",2).groupBy("interviewer_id").listAs(HrAccountInstanceResponse.class);

        Map<Long, Long> resumeCountMap = resumeCountList.stream()
                .filter(vo -> vo.getAccountInstanceId() != null)
                .collect(Collectors.toMap(
                        HrAccountInstanceResponse::getAccountInstanceId,
                        HrAccountInstanceResponse::getResumeCount,
                        (existing, replacement) -> existing,
                        HashMap::new
                ));

        Map<Long, Long> interviewCountMap = interviewCountList.stream()
                .filter(vo -> vo.getAccountInstanceId() != null)
                .collect(Collectors.toMap(
                        HrAccountInstanceResponse::getAccountInstanceId,
                        HrAccountInstanceResponse::getInterviewCount,
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
        resultPage.getRecords().stream().forEach(hrAccountInstanceResponse -> {
            hrAccountInstanceResponse.setResumeCount(resumeCountMap.get(hrAccountInstanceResponse.getAccountInstanceId()) == null
                    ? 0L:resumeCountMap.get(hrAccountInstanceResponse.getAccountInstanceId()));
            hrAccountInstanceResponse.setInterviewCount(interviewCountMap.get(hrAccountInstanceResponse.getAccountInstanceId()) == null
                    ? 0L:interviewCountMap.get(hrAccountInstanceResponse.getAccountInstanceId()));
        });
        return resultPage;
    }

    /**
     * 删除
     * 删除面试官
     * @param accountInstanceId
     * @return
     */
    @Transactional
    public Boolean delete(Long accountInstanceId) {
        AccountInstanceEntity accountInstanceEntity = accountInstanceService.getById(accountInstanceId);
        if (accountInstanceEntity == null || Objects.equals(CommonDelEnum.DELETE.getCode(), accountInstanceEntity.getDeleted())) {
            log.warn("招聘官删除失败,未找到有效的招聘官：{}", accountInstanceId);
            return false;
        }

        boolean update = UpdateChain.of(AccountInstanceEntity.class)
                .set(AccountInstanceEntity::getDeleted, CommonDelEnum.DELETE.getCode())
                .set(AccountInstanceEntity::getUt, new Date())
                .set(AccountInstanceEntity::getVer, accountInstanceEntity.getVer() + 1)
                .eq(AccountInstanceEntity::getAccountInstanceId, accountInstanceId, Objects.nonNull(accountInstanceId))
                .eq(AccountInstanceEntity::getVer, accountInstanceEntity.getVer())
                .update();
        if (!update) {
            log.warn("招聘官删除失败,操作失败请重试：{}", accountInstanceId);
            throw new UimException(CommonStatusCode.FAILED);
        }
        return true;
    }
}
