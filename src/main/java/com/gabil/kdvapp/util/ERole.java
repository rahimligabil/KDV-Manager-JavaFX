package com.gabil.kdvapp.util;

import lombok.Getter;

/**
 * 📌 Kullanıcı Rollerini Tanımlayan Enum
 */
@Getter
public enum ERole {
    USER("Kullanıcı"),
    MODERATOR("Moderatör"),
    ADMIN("Yönetici");

    private final String description;

    ERole(String description) {
        this.description = description;
    }

    /**
     * 📌 String’den Enum’a güvenli dönüşüm yapar.
     */
    public static ERole fromString(String role) {
        try {
            return ERole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("❌ Geçersiz rol: " + role);
        }
    }

    @Override
    public String toString() {
        return description; // ComboBox’ta görünen metin
    }
}