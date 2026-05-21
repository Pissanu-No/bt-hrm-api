package com.bakertilly.bt_hrm_api.core.utils;

import jakarta.servlet.http.HttpServletRequest;

public class ClientUtils {

    public static String getHeaderAttribute(HttpServletRequest req, String name) {
        String v = req.getHeader(name);
        return (v == null || v.isBlank()) ? null : v;
    }

    public static String resolveClientIp(HttpServletRequest req) {
        String h = req.getHeader("X-Forwarded-For");
        if (h != null && !h.isBlank()) {
            String first = h.split(",")[0].trim();
            if (!first.isEmpty()) return first;
        }
        h = req.getHeader("X-Real-IP");
        if (h != null && !h.isBlank()) return h.trim();
        return req.getRemoteAddr(); // fallback
    }
}
