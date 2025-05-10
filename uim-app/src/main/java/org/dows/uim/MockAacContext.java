package org.dows.uim;

import lombok.extern.slf4j.Slf4j;
import org.dows.rade.aac.AacContext;
import org.dows.rade.aac.AacUser;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class MockAacContext implements AacContext {
    public AacUser getAacUser(){
        AacUser aacUser = new
                AacUser() {
                    @Override
                    public Long getAccountId() {
                        return 0L;
                    }

                    @Override
                    public Long getUserId() {
                        return 0L;
                    }

                    @Override
                    public Long getOrgRootId() {
                        return 273740080417443840L;
                    }

                    @Override
                    public Long getOrgTreeId() {
                        return 0L;
                    }

                    @Override
                    public List<Long> getRoleIds() {
                        return List.of();
                    }

                    @Override
                    public List<Integer> getAccountTypes() {
                        return List.of();
                    }

                    @Override
                    public boolean isSuperAccount() {
                        return false;
                    }

                    @Override
                    public String getNickname() {
                        return "";
                    }

                    @Override
                    public String getUsername() {
                        return "";
                    }

                    @Override
                    public String getAvatar() {
                        return "";
                    }

                    @Override
                    public String getTelephone() {
                        return "";
                    }

                    @Override
                    public String getEmail() {
                        return "";
                    }

                    @Override
                    public Integer getState() {
                        return 0;
                    }

                    @Override
                    public Integer getIdentifierType() {
                        return 0;
                    }
                };
        return aacUser;
    };
}
