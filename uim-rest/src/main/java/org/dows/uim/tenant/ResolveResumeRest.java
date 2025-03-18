package org.dows.uim.tenant;

import lombok.RequiredArgsConstructor;
import org.dows.uim.api.ResolveResumeApi;
import org.dows.uim.api.request.ResolveResumeRequest;
import org.dows.uim.api.response.ResolveResumeResponse;
import org.dows.uim.biz.ResolveResumeBiz;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ResolveResumeRest implements ResolveResumeApi {

    private final ResolveResumeBiz resolveResumeBiz;


    @PostMapping("/asdsadada")
    public ResolveResumeResponse ddd2(ResolveResumeRequest ResolveResumeRequest) {
        return null;
    }


    @Override
    public ResolveResumeResponse ddd(ResolveResumeRequest ResolveResumeRequest) {
        return null;
    }
}
