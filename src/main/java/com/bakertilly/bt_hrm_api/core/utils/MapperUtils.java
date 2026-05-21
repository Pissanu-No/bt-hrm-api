package com.bakertilly.bt_hrm_api.core.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class MapperUtils {

    private MapperUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String toStringSafe(Object obj) {
        return obj != null ? obj.toString() : null;
    }

    public static Integer toIntegerSafe(Object obj) {
        if (obj instanceof Integer integer) {
            return integer;
        }
        try {
            return obj != null ? Integer.parseInt(obj.toString()) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long toLongSafe(Object obj) {
        if (obj instanceof Long l) {
            return l;
        }
        try {
            return obj != null ? Long.parseLong(obj.toString()) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Boolean toBooleanSafe(Object obj) {
        if (obj instanceof Boolean b) {
            return b;
        }
        return obj != null ? Boolean.parseBoolean(obj.toString()) : null;
    }

    public static BigDecimal toBigDecimalSafe(Object obj) {
        if (obj instanceof BigDecimal bd) {
            return bd;
        }
        try {
            return obj != null ? new BigDecimal(obj.toString()) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static LocalDate toLocalDateSafe(Object obj) {
        if (obj instanceof LocalDate date) {
            return date;
        }
        try {
            return obj != null ? LocalDate.parse(obj.toString()) : null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static Date toDateSafe(Object obj) {
        if (obj instanceof Date date) {
            return date;
        }
        if (obj != null) {
            try {
                String value = obj.toString().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.parse(value);
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }
}
