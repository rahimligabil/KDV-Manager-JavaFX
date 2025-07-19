package com.gabil.kdvapp.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHelper {
    private static Locale currentLocale = new Locale("tr", "TR");

    public static void toggleLanguage() {
        currentLocale = currentLocale.getLanguage().equals("tr") ? new Locale("en", "US") : new Locale("tr", "TR");
    }

    public static ResourceBundle getBundle() {
        return ResourceBundle.getBundle("i18n.messages", currentLocale);
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}
