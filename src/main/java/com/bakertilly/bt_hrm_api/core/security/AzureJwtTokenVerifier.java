package com.bakertilly.bt_hrm_api.core.security;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class AzureJwtTokenVerifier {

    private final String tenantId;
    private JwtDecoder jwtDecoder;

    public AzureJwtTokenVerifier(@Value("${azure.activedirectory.tenant-id:}") String tenantId) {
        this.tenantId = tenantId;
    }

    private JwtDecoder getJwtDecoder() {
        if (jwtDecoder != null) {
            return jwtDecoder;
        }
        if (tenantId == null || tenantId.isBlank()) {
            throw new IllegalStateException("Azure tenant ID is not configured");
        }
        String issuer = "https://login.microsoftonline.com/" + tenantId + "/v2.0";
        this.jwtDecoder = JwtDecoders.fromIssuerLocation(issuer);
        return jwtDecoder;
    }

    public Jwt decode(String token) {
        return getJwtDecoder().decode(token);
    }

    public JWTClaimsSet getClaims(String token) {
        Jwt jwt = decode(token);
        Map<String, Object> claims = jwt.getClaims();

        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String || value instanceof Number || value instanceof Boolean || value instanceof Date) {
                builder.claim(entry.getKey(), value);
            } else if (value instanceof Instant) {
                builder.claim(entry.getKey(), Date.from((Instant) value));
            } else {
                builder.claim(entry.getKey(), value.toString());
            }
        }

        return builder.build();
    }


    public String getClaim(String token, String claimName) {
        Jwt jwt = decode(token);
        return jwt.getClaimAsString(claimName);
    }
}
