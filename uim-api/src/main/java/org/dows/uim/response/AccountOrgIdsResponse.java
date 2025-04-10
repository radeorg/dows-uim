package org.dows.uim.response;

import lombok.Data;

import java.util.List;

@Data
public class AccountOrgIdsResponse {
    private List<Long> accountOrgId;
}
