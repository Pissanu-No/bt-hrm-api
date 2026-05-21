package com.bakertilly.bt_hrm_api.app.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard API response")
public class ApiResponse<T> {
    private static final ZoneId API_ZONE = ZoneId.of("Asia/Bangkok");

    @Schema(description = "Operation result", example = "true")
    private boolean success;

    @Schema(description = "Response message", example = "Success")
    private String message;

    @Schema(description = "Response data")
    private T data;

    @Schema(description = "Validation or business errors")
    private List<ApiFieldError> errors;

    @Schema(description = "Response timestamp", example = "2026-05-05T10:00:00+07:00")
    private OffsetDateTime timestamp;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Success")
                .data(data)
                .timestamp(now())
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message, List<ApiFieldError> errors) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .timestamp(now())
                .build();
    }

    private static OffsetDateTime now() {
        return OffsetDateTime.now(API_ZONE);
    }
}
