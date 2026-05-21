package com.bakertilly.bt_hrm_api.app.audit.aspect;

import com.bakertilly.bt_hrm_api.app.audit.dto.AuditLogEntry;
import com.bakertilly.bt_hrm_api.app.audit.service.AuditLogService;
import com.bakertilly.bt_hrm_api.core.model.JwtClaim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditLogAspect {
    private static final int MAX_JSON_LENGTH = 8000;

    private final AuditLogService auditLogService;
    private final ObjectMapper objectMapper;

    @AfterReturning(
            pointcut = "within(@org.springframework.web.bind.annotation.RestController *)"
                    + " && within(com.bakertilly.bt_hrm_api.app..*)"
                    + " && !within(com.bakertilly.bt_hrm_api.app.audit..*)",
            returning = "result")
    public void recordMutation(JoinPoint joinPoint, Object result) {
        HttpServletRequest request = currentRequest();
        if (request == null || !isMutationMethod(request.getMethod())) {
            return;
        }

        Map<String, String> pathVariables = getPathVariables(request);
        String username = resolveUsername();

        AuditLogEntry entry = AuditLogEntry.builder()
                .userId(null)
                .username(username)
                .actionType(request.getMethod().toUpperCase(Locale.ROOT))
                .moduleCode(resolveModuleCode(request.getRequestURI()))
                .tableName(resolveTableName(request.getRequestURI()))
                .recordId(resolveRecordId(pathVariables))
                .oldValue(null)
                .newValue(toJson(buildPayload(joinPoint, result, pathVariables)))
                .ipAddress(resolveClientIp(request))
                .userAgent(truncate(request.getHeader("User-Agent"), 1000))
                .requestId(truncate(resolveRequestId(request), 100))
                .build();

        auditLogService.record(entry);
    }

    private static HttpServletRequest currentRequest() {
        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes attributes) {
            return attributes.getRequest();
        }
        return null;
    }

    private static boolean isMutationMethod(String method) {
        return "POST".equalsIgnoreCase(method)
                || "PUT".equalsIgnoreCase(method)
                || "PATCH".equalsIgnoreCase(method)
                || "DELETE".equalsIgnoreCase(method);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getPathVariables(HttpServletRequest request) {
        Object variables = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (variables instanceof Map<?, ?> map) {
            Map<String, String> result = new LinkedHashMap<>();
            map.forEach((key, value) -> result.put(String.valueOf(key), value == null ? null : String.valueOf(value)));
            return result;
        }
        return Map.of();
    }

    private Map<String, Object> buildPayload(JoinPoint joinPoint, Object result, Map<String, String> pathVariables) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("pathVariables", pathVariables);
        payload.put("arguments", joinPoint.getArgs());
        payload.put("response", unwrapResponseEntity(result));
        return payload;
    }

    private static Object unwrapResponseEntity(Object result) {
        if (result instanceof ResponseEntity<?> responseEntity) {
            return responseEntity.getBody();
        }
        return result;
    }

    private String toJson(Object value) {
        try {
            return truncate(objectMapper.writeValueAsString(value), MAX_JSON_LENGTH);
        } catch (JsonProcessingException | RuntimeException ex) {
            return "{\"serialization\":\"failed\"}";
        }
    }

    private static String resolveModuleCode(String uri) {
        if (uri == null) {
            return null;
        }
        if (uri.contains("/companies")) return "COMPANY";
        if (uri.contains("/branches")) return "BRANCH";
        if (uri.contains("/locations")) return "LOCATION";
        if (uri.contains("/departments")) return "DEPARTMENT";
        if (uri.contains("/positions")) return "POSITION";
        if (uri.contains("/job-levels")) return "JOB_LEVEL";
        if (uri.contains("/cost-centers")) return "COST_CENTER";
        if (uri.contains("/lookup-groups")) return "LOOKUP_GROUP";
        if (uri.contains("/lookup-values")) return "LOOKUP_VALUE";
        if (uri.contains("/menu-items")) return "SYS_MENU_ITEM";
        if (uri.contains("/emergency-contacts")) return "EMPLOYEE_EMERGENCY_CONTACT";
        if (uri.contains("/educations")) return "EMPLOYEE_EDUCATION";
        if (uri.contains("/certifications")) return "EMPLOYEE_CERTIFICATION";
        if (uri.contains("/attachment-files")) return "EMPLOYEE_ATTACHMENT_FILE";
        if (uri.contains("/addresses")) return "EMPLOYEE_ADDRESS";
        if (uri.contains("/employees")) return "EMPLOYEE";
        return "UNKNOWN";
    }

    private static String resolveTableName(String uri) {
        if (uri == null) {
            return null;
        }
        if (uri.contains("/companies")) return "hris_master_company";
        if (uri.contains("/branches")) return "hris_master_branch";
        if (uri.contains("/locations")) return "hris_master_location";
        if (uri.contains("/departments")) return "hris_master_department";
        if (uri.contains("/positions")) return "hris_master_position";
        if (uri.contains("/job-levels")) return "hris_master_job_level";
        if (uri.contains("/cost-centers")) return "hris_master_cost_center";
        if (uri.contains("/lookup-groups")) return "hris_master_lookup_group";
        if (uri.contains("/lookup-values")) return "hris_master_lookup_value";
        if (uri.contains("/menu-items")) return "hris_master_menu_item";
        if (uri.contains("/emergency-contacts")) return "hris_transaction_emergency_contact";
        if (uri.contains("/educations")) return "hris_transaction_education";
        if (uri.contains("/certifications")) return "hris_transaction_certification";
        if (uri.contains("/attachment-files")) return "hris_transaction_attachment_file";
        if (uri.contains("/addresses")) return "hris_transaction_address";
        if (uri.contains("/employees")) return "hris_transaction_employee";
        return null;
    }

    private static String resolveRecordId(Map<String, String> pathVariables) {
        for (String key : new String[]{
                "emergencyContactId", "educationId", "certificationId", "attachmentFileId", "addressId",
                "lookupGroupId", "lookupValueId", "menuItemId",
                "companyId", "branchId", "locationId", "departmentId", "positionId", "jobLevelId", "costCenterId", "employeeId"}) {
            String value = pathVariables.get(key);
            if (value != null) {
                return truncate(value, 60);
            }
        }
        return null;
    }

    private static String resolveClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return truncate(forwardedFor.split(",")[0].trim(), 100);
        }
        return truncate(request.getRemoteAddr(), 100);
    }

    private static String resolveRequestId(HttpServletRequest request) {
        String requestId = request.getHeader("X-Request-Id");
        if (requestId == null || requestId.isBlank()) {
            requestId = request.getHeader("X-Correlation-Id");
        }
        return requestId;
    }

    private static String resolveUsername() {
        String username = resolveClaim("username", "preferred_username", "email", "name", "sub");
        if (username != null) {
            return truncate(username, 100);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            return null;
        }
        return truncate(authentication.getName(), 100);
    }

    private static String resolveClaim(String... claimNames) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof JwtClaim jwtClaim)
                || jwtClaim.getAttrs() == null) {
            return null;
        }

        for (String claimName : claimNames) {
            Object value = jwtClaim.getAttrs().get(claimName);
            if (value != null) {
                return String.valueOf(value);
            }
        }
        return null;
    }

    private static String truncate(String value, int maxLength) {
        if (value == null || value.length() <= maxLength) {
            return value;
        }
        return value.substring(0, maxLength);
    }
}
