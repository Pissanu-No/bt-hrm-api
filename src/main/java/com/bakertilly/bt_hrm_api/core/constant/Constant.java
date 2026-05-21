package com.bakertilly.bt_hrm_api.core.constant;

public class Constant {
    public enum ResponseMessage {
        ;
        public static final String SUCCESS = "Success.";
        public static final String FAILURE = "Failure.";
        public static final String ERROR = "Error.";
        public static final String DATA_NOT_FOUND = "Data not found.";
        public static final String DATA_DUPLICATE = "Data is duplicated.";
        public static final String USER_NOT_FOUND = "User not found.";
        public static final String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error.";
        public static final String ERROR_FILE_STORAGE_UPLOAD = "File storage upload error.";
        public static final String ERROR_FILE_SIZE_LIMIT_EXCEEDED = "File size limit exceeded 2 mb";
        public static final String ERROR_FILE_STORAGE_DOWNLOAD = "File storage download error.";
        public static final String ERROR_FILE_NOT_FOUND = "File not found.";
        public static final String ERROR_FILE_STORAGE_DELETE = "File storage delete error.";
        public static final String ERROR_BAD_REQUEST = "Bad request.";
        public static final String PASSWORD_RESET_EXP = "The password reset link has expired";
        public static final String PASSWORD_NOT_MATCH = "Password not match";
        public static final String PASSWORD_NOT_STRONG = "Password not strong enough";
        public static final String ERROR_PASSWORD_INCORRECT = "Password Incorrect.";
        public static final String EMPLOYEE_HAS_REVIEWER = "Employee has reviewer";
        public static final String NO_PERMISSION_SUPER_ADMIN = "Operation not allowed: Super Admin roles are restricted from being created, edited, or deleted.";
        public static final String INVALID_EMAIL_FORMAT_MSG = "Invalid email format.";
        public static final String EXTERNAL_SERVER_ERROR_MSG = "External Server Error.";
        public static final String UNKNOWN_ERROR = "Unknown error";
        public static final String PENDING_TO_SYNC_KPI_PLEASE_WAIT = "Pending to sync KPI. Please wait.";
        public static final String REFRESH_TOKEN_INVALID_EXP = "Refresh token invalid or expired";
        public static final String INVALID_TOKEN_TYPE = "Invalid token type";
        public static final String VALIDATION_FAILED  = "Validation failed";
        public static final String EMAIL_ALREADY_EXISTS_IN_RECOVERY_SYS = "Email already exists in our recovery system. Please try recovering your account instead.";
        public static final String MASTER_DATA_INVALID_TYPE = "The selected master data has an invalid type. Expected '%s' but received '%s'.";
        public static final String ACCOUNT_INACTIVE = "Your account is inactive";
        public static final String MISSING_HEADER_MESSAGE = "Missing header attribute in your request.";
        public static final String ACCESS_TOKEN_REFRESH_TOKEN_NULL = "Access token or refresh token is null";
        public static final String CORPORATE_NOT_FOUND = "Corporate not found";
        public static final String ACCESS_DENIED = "Access denied";
    }

    public enum JwtProperties{
        ;
        public static final String ALGORITHM_RSA = "RSA";
        public static final int TOKEN_EXPIRATION = 3600;
        public static final int REFRESH_EXPIRATION = 604800;
    }

    public enum ResponseCode {
        ;
        public static final String SUCCESS_CODE = "1000";
        public static final String SUCCESS_WITH_CONDITION = "1001";
        public static final String ERROR_CODE_BUSINESS = "2000";
        public static final String ERROR_CODE_INVALID_REQUEST = "2001";
        public static final String ERROR_CODE_DATA_NOT_FOUND = "2002";
        public static final String ERROR_CODE_ACCESS_DENIED = "2003";
        public static final String ERROR_CODE_FILE_UPLOAD = "3001";
        public static final String ERROR_CODE_FILE_DOWNLOAD = "3002";
        public static final String ERROR_CODE_FILE_DELETE = "3003";
        public static final String FAIL_CODE_INTERNAL = "3000";
        public static final String FAIL_CODE_EXTERNAL = "4000";
        public static final String MISSING_HEADER = "4001";
        public static final String INTERNAL_SERVER_ERROR_CODE = "9999";

    }

    public enum activities {
        ;
        //data activities
        public static final String DECRYPT = "decrypt";
        public static final String ENCRYPT = "encrypt";
    }
}
