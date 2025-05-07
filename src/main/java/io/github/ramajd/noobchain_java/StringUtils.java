package io.github.ramajd.noobchain_java;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class StringUtils {

    public static String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hashString.append('0');
                hashString.append(hex);
            }

            return hashString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
