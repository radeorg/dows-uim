package org.dows.uim.api;

import org.springframework.web.bind.annotation.PostMapping;

public interface ResolveResumeApi {

     @PostMapping("/resolveResume")
     void ddd(String ResolveResumeRequest);
}
