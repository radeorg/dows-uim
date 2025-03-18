package org.dows.uim.api;

import org.dows.uim.api.request.ResolveResumeRequest;
import org.dows.uim.api.response.ResolveResumeResponse;
import org.springframework.web.bind.annotation.PostMapping;

public interface ResolveResumeApi {

     @PostMapping("/resolveResume")
     ResolveResumeResponse ddd(ResolveResumeRequest ResolveResumeRequest);
}
