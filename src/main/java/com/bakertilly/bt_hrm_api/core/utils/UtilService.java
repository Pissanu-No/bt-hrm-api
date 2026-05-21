package com.bakertilly.bt_hrm_api.core.utils;

import com.bakertilly.bt_hrm_api.core.model.PageBodyModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Date;

public interface UtilService {


    String textValueEncAndDec(String secretKey, String text, String action) throws InvalidKeySpecException
            , NoSuchAlgorithmException, NoSuchPaddingException
            , InvalidKeyException, InvalidAlgorithmParameterException
            , IllegalBlockSizeException, BadPaddingException;

    boolean isEmailValid(@NotNull String email);

    boolean isTimeExceeded(Date givenTime);

    Pageable pageBodyconvertToPageable(PageBodyModel pageBodyModel);

    PageBodyModel pageableConvertToPageBodyModel(Pageable pageable, long totalElements, int totalPages);

    String generatePassword(int length);

    boolean isValidPassword(String password);

    String generateToken();

    String encodeToBase64(String value);

    String getInitialName(String fullName);

    Timestamp toTimestamp(Object v);

    String replaceFileName(String originalFileName);

    String[] splitStringBy(String content, String delimiter);

    String getSecretValue(String secretName);
}
