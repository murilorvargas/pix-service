package com.pix.pix_service.infrastructure.gateways.qi_tech;

import io.jsonwebtoken.Jwts;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;

@Component
public class QiTechSigner {

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
            .withZone(ZoneOffset.UTC);

    private final PrivateKey privateKey;
    private final String apiClientKey;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public QiTechSigner(
            @Value("${qitech.private-key-path}") String privateKeyPath,
            @Value("${qitech.api-client-key}") String apiClientKey
    ) {
        this.privateKey = loadPrivateKey(privateKeyPath);
        this.apiClientKey = apiClientKey;
    }

    public Map<String, String> sign(String method, String endpoint, String body) {
        String timestamp = ISO_FORMATTER.format(Instant.now());
        String bodyMd5 = md5(body);

        String token = Jwts.builder()
                .claim("method", method)
                .claim("uri", endpoint)
                .claim("timestamp", timestamp)
                .claim("payload_md5", bodyMd5)
                .signWith(privateKey, Jwts.SIG.ES512)
                .compact();

        return Map.of(
                "Authorization", token,
                "API-CLIENT-KEY", apiClientKey
        );
    }

    private String md5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available", e);
        }
    }

    private PrivateKey loadPrivateKey(String path) {
        try {
            String keyContent = Files.readString(Path.of(path));
            String encoded = keyContent
                    .replace("-----BEGIN EC PRIVATE KEY-----", "")
                    .replace("-----END EC PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(encoded);
            KeyFactory keyFactory = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);

            ECPrivateKey ecKey = ECPrivateKey.getInstance(decoded);
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp521r1");
            return keyFactory.generatePrivate(new ECPrivateKeySpec(ecKey.getKey(), ecSpec));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load private key", e);
        }
    }

    public String getApiClientKey() {
        return apiClientKey;
    }
}
