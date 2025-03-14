package org.dows.uim.tenant;

import lombok.RequiredArgsConstructor;
import org.dows.uim.api.ResolveResumeApi;
import org.dows.uim.biz.ResolveResumeBiz;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ResolveResumeRest implements ResolveResumeApi {

    private final ResolveResumeBiz resolveResumeBiz;



    @Override
    public void ddd(String ResolveResumeRequest) {

    }
}
