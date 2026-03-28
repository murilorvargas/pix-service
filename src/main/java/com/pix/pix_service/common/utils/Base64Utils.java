package com.pix.pix_service.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Base64Utils {

    private Base64Utils() {}

    public static String decode(String encoded) {
        return new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
    }
}
