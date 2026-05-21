package com.bakertilly.bt_hrm_api.core.utils.impl;


import com.azure.security.keyvault.secrets.SecretClient;
import com.bakertilly.bt_hrm_api.core.model.PageBodyModel;
import com.bakertilly.bt_hrm_api.core.utils.UtilService;
import com.google.common.base.Strings;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bakertilly.bt_hrm_api.core.constant.Constant.activities.DECRYPT;
import static com.bakertilly.bt_hrm_api.core.constant.Constant.activities.ENCRYPT;

@Service
public class UtilServiceImpl implements UtilService {

    @Value("${text.iv.spec}")
    private String ivSpecKey;

    @Value("${text.salt.key}")
    private String saltKey;

    private SecretClient secretClient;

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";
    private static final SecureRandom random = new SecureRandom();

    public UtilServiceImpl(SecretClient secretClient) {
        this.secretClient = secretClient;
    }

    @Override
    public String textValueEncAndDec(String secretKey, String text, String action) throws InvalidKeySpecException
            , NoSuchAlgorithmException, NoSuchPaddingException
            , InvalidKeyException, InvalidAlgorithmParameterException
            , IllegalBlockSizeException, BadPaddingException {
        byte[] iv = Base64.getDecoder().decode(ivSpecKey);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), saltKey.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec generateSecretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        return switch (action) {
            case DECRYPT -> {
                cipher.init(Cipher.DECRYPT_MODE, generateSecretKey, ivSpec);
                yield new String(cipher.doFinal(Base64.getDecoder().decode(text)));
            }
            case ENCRYPT -> {
                cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey, ivSpec);
                yield Base64.getEncoder()
                        .encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
            }
            default -> null;
        };
    }

    @Override
    public boolean isEmailValid(@NotNull String email) {
        return Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
                .matcher(email)
                .matches();
    }

    @Override
    public boolean isTimeExceeded(Date givenTime) {
        return givenTime.before(new Date());
    }


    @Override
    public Pageable pageBodyconvertToPageable(PageBodyModel pageBodyModel) {
        int page = Strings.isNullOrEmpty(pageBodyModel.getPage()) ? 0 : Integer.parseInt(pageBodyModel.getPage()) - 1;
        int pageSize = Strings.isNullOrEmpty(pageBodyModel.getPageSize()) ? 10 : Integer.parseInt(pageBodyModel.getPageSize());

        String sortBy = pageBodyModel.getSortBy();
        String sortDir = pageBodyModel.getSortDirection();

        List<String> sortByList = !Strings.isNullOrEmpty(sortBy)
                ? Arrays.asList(sortBy.split(","))
                : Collections.singletonList("createdDate"); // ✅ default

        List<String> sortDirList = !Strings.isNullOrEmpty(sortDir)
                ? Arrays.asList(sortDir.split(","))
                : Collections.singletonList("asc"); // ✅ default

        List<Sort.Order> orders = new ArrayList<>();
        for (int i = 0; i < sortByList.size(); i++) {
            String field = sortByList.get(i).trim();
            String dir = (i < sortDirList.size()) ? sortDirList.get(i).trim() : "asc";

            Sort.Direction direction = Sort.Direction.fromOptionalString(dir.toUpperCase())
                    .orElse(Sort.Direction.ASC);

            orders.add(new Sort.Order(direction, field));
        }

        return PageRequest.of(page, pageSize, Sort.by(orders));
    }


    @Override
    public PageBodyModel pageableConvertToPageBodyModel(Pageable pageable, long totalElements, int totalPages) {
        return PageBodyModel
                .builder()
                .page(String.valueOf(pageable.getPageNumber() +1 ))
                .pageSize(String.valueOf(pageable.getPageSize()))
                .total(String.valueOf(totalElements))
                .totalPage(String.valueOf(totalPages))
                .sortDirection(pageable.getSort().iterator().next().getDirection().name())
                .sortBy(pageable.getSort().iterator().next().getProperty())
                .build();
    }

    @Override
    public String generatePassword(int length) {
        String allCharacters = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;
        SecureRandom rd = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        // Ensure that the password contains at least one character from each category
        password.append(LOWERCASE.charAt(rd.nextInt(LOWERCASE.length())));
        password.append(UPPERCASE.charAt(rd.nextInt(UPPERCASE.length())));
        password.append(DIGITS.charAt(rd.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(rd.nextInt(SPECIAL_CHARACTERS.length())));

        // Fill the remaining length with secureRandom characters
        for (int i = password.length(); i < length; i++) {
            password.append(allCharacters.charAt(rd.nextInt(allCharacters.length())));
        }

        // Shuffle the password to ensure randomness
        StringBuilder shuffledPassword = new StringBuilder(password.length());
        while (!password.isEmpty()) {
            int randomIndex = rd.nextInt(password.length());
            shuffledPassword.append(password.charAt(randomIndex));
            password.deleteCharAt(randomIndex);
        }

        return shuffledPassword.toString();
    }

    @Override
    public boolean isValidPassword(String password) {
        // create Pattern and Matcher
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher = pattern.matcher(password);

        // check password match regex
        return matcher.matches();
    }

    @Override
    public String generateToken() {
        byte[] token = new byte[24]; // create token size 24 bytes
        random.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token); // convert to Base64
    }

    @Override
    public String encodeToBase64(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getInitialName(String fullName) {
        if (Strings.isNullOrEmpty(fullName)) {
            return "";
        }
        String[] parts = fullName.split("\\s+");
        StringBuilder initials = new StringBuilder();
        for (String part : parts) {
            if (part.matches("[a-zA-Z].*")) {
                initials.append(part.charAt(0));
            }
        }
        return initials.toString().toUpperCase();
    }

    @Override
    public Timestamp toTimestamp(Object v) {
        if (v == null) throw new IllegalArgumentException("exp is null");

        // 1) already known type
        if (v instanceof Timestamp ts) return ts;
        if (v instanceof Date d)       return new Timestamp(d.getTime());
        if (v instanceof Instant i)    return Timestamp.from(i);

        // 2) Number (epoch sec/millis)
        if (v instanceof Number n) {
            long x = n.longValue();
            return new Timestamp(x >= 1_000_000_000_000L ? x : x * 1000L);
        }

        // 3) String
        String s = v.toString().trim();
        // 3.1 all number -> epoch
        if (s.matches("\\d+")) {
            long x = Long.parseLong(s);
            return new Timestamp(x >= 1_000_000_000_000L ? x : x * 1000L);
        }

        // 3.2 ISO-8601 (example 2025-09-29T12:02:30+07:00)
        try {
            Instant i = Instant.parse(s);
            return Timestamp.from(i);
        } catch (Exception ignore) {}

        // 3.3 Date.toString() type: "EEE MMM dd HH:mm:ss zzz yyyy" example "Mon Sep 29 12:02:30 ICT 2025"
        try {
            var sdf = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Date d = sdf.parse(s);
            return new Timestamp(d.getTime());
        } catch (java.text.ParseException e) {
            throw new IllegalArgumentException("Unsupported exp format: " + s, e);
        }
    }

    @Override
    public String replaceFileName(String originalFileName) {

        if (originalFileName == null || originalFileName.isBlank()) {
            return "attachment";
        }

        // แยกชื่อไฟล์กับนามสกุล
        String fileName = originalFileName.trim();

        String extension = "";
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            extension = fileName.substring(lastDotIndex);
            fileName = fileName.substring(0, lastDotIndex);
        }

        // Replace อักขระต้องห้าม (/, \, :, *, ?, ", <, >, |, space)
        String safeName = fileName
                .toLowerCase()
                .replaceAll("[^a-z0-9_-]", "_")
                .replaceAll("_+", "_");

        // กันชื่อว่าง
        if (safeName.isBlank()) {
            safeName = "attachment";
        }

        return safeName + extension.toLowerCase();
    }

    @Override
    public String[] splitStringBy(String content, String delimiter) {
        if (content == null || content.isEmpty()) {
            return new String[0];
        }

        if (delimiter == null || delimiter.isEmpty()) {
            return new String[] { content };
        }

        return content.split(Pattern.quote(delimiter));
    }

    @Override
    public String getSecretValue(String secretName) {
        return secretClient.getSecret(secretName).getValue().replaceAll("\\s+", "");
    }
}
