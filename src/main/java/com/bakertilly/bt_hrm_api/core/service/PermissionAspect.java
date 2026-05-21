package com.bakertilly.bt_hrm_api.core.service;

import com.bakertilly.bt_hrm_api.core.model.JwtClaim;
import com.bakertilly.bt_hrm_api.core.model.Permission;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAspect {

    @Before("@annotation(permission)")
    public void checkPermission(Permission permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
        }

        if (authentication.getPrincipal() instanceof JwtClaim) {
            return;
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
    }
}
