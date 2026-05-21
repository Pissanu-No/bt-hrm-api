package com.bakertilly.bt_hrm_api.core.security;

import com.bakertilly.bt_hrm_api.core.model.JwtClaim;
import com.bakertilly.bt_hrm_api.core.service.JwtUtilService;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Component
@AllArgsConstructor
public class AuthenFilter extends OncePerRequestFilter {

    private final JwtUtilService jwtUtilService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Let the centralized Spring CORS configuration handle preflight headers.
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // ====== Bypass Paths That Don't Require Auth ======
        String path = request.getRequestURI();
        if (!path.contains("/v1/s/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ====== Auth Logic (Only for /v1/s/) ======
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new BadCredentialsException("Missing or invalid Authorization header");
            }

            String jwtToken = token.split(" ")[1];
            Claims claims = jwtUtilService.deCode(jwtToken);

            if (!path.contains("/refresh-token")) {
                String type = claims.get("type", String.class);
                if (!"access".equals(type)) {
                    throw new AccessDeniedException("Only access tokens allowed on this endpoint");
                }
            }

            Map<String, Object> claimsMap = SignedJWT.parse(jwtToken).getJWTClaimsSet().getClaims();

            JwtClaim jwtClaim = JwtClaim.builder()
                    .token(jwtToken)
                    .expire(claims.get("exp", Long.class))
                    .iat(claims.get("iat", Long.class))
                    .attrs(claimsMap)
                    .build();

            Authentication auth = new JwtAuthenticationToken(jwtClaim, token, AuthorityUtils.NO_AUTHORITIES);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (ExpiredJwtException ex) {
            logger.warn("Access token expired: {}", ex);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Access token has expired");
            return;
        } catch (JwtException | IllegalArgumentException | BadCredentialsException ex) {
            logger.error("Invalid token format or signature: ", ex);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return;
        } catch (AccessDeniedException ex) {
            logger.error("Authentication failed", ex);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Authentication failed");
            return;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Let downstream exceptions bubble up to controller/exception handlers.
        filterChain.doFilter(request, response);
    }

}
