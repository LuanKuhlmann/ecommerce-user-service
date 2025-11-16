package io.ecommerce.group.user_service.Util;

public class ExtractNameInitialsUtil {

    public ExtractNameInitialsUtil() {
    }

    public static String extractInitials(String name) {
        if (name == null || name.isBlank()) {
            return "";
        }

        String[] partes = name.trim().split("\\s+");
        if (partes.length == 1) {
            return partes[0].substring(0, 1).toUpperCase();
        }

        String firstLetter = partes[0].substring(0, 1);
        String lastLetter = partes[partes.length - 1].substring(0, 1);

        return (firstLetter + lastLetter).toUpperCase();
    }
}
