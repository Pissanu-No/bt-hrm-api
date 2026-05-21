package com.bakertilly.bt_hrm_api.core.security;


import com.bakertilly.bt_hrm_api.core.model.JwtClaim;
import com.google.common.base.Strings;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 6673630888624971991L;

    private JwtClaim claim;
    private String token;

    public JwtAuthenticationToken(JwtClaim claims, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.claim = claims;
        this.token = token;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        if (!Strings.isNullOrEmpty(claim.getToken())) {
            return claim;
        } else {
            return null;
        }
    }

}
