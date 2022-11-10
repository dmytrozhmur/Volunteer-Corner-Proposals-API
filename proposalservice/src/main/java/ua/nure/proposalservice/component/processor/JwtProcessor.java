package ua.nure.proposalservice.component.processor;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtProcessor {
    private static final String ID_KEY = "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier";
    private SecretKey secret;

    public JwtProcessor(@Value("${jwt.signature.key}") String keyword) {
        secret = generateSecretKey(keyword);
    }

    public String extractId(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().get(ID_KEY, String.class);
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    private SecretKey generateSecretKey(String keyword) {
        String encodedKeyword =
                Base64.getEncoder().withoutPadding().encodeToString(keyword.getBytes());
        byte[] decodedKey = Base64.getDecoder().decode(encodedKeyword);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}
