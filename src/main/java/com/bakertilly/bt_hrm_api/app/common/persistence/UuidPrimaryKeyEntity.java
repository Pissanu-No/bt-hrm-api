package com.bakertilly.bt_hrm_api.app.common.persistence;

import java.util.UUID;

public interface UuidPrimaryKeyEntity {
    String getPrimaryKeyValue();

    void setPrimaryKeyValue(String primaryKeyValue);

    String getPrimaryKeyFieldName();

    default void ensureUuidPrimaryKey() {
        String primaryKeyValue = getPrimaryKeyValue();
        if (primaryKeyValue == null || primaryKeyValue.isBlank()) {
            setPrimaryKeyValue(UUID.randomUUID().toString());
            return;
        }

        try {
            UUID uuid = UUID.fromString(primaryKeyValue);
            setPrimaryKeyValue(uuid.toString());
        } catch (IllegalArgumentException exception) {
            throw new IllegalStateException(getPrimaryKeyFieldName() + " must be a UUID value", exception);
        }
    }
}
