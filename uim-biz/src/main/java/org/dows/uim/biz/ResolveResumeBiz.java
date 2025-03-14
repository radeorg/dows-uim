package org.dows.uim.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.handler.ResolveResumeHandler;
import org.dows.uim.service.ExamInstanceService;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ResolveResumeBiz {

    private final ExamInstanceService examInstanceService;

    private final ResolveResumeHandler resolveResumeHandler;




}
