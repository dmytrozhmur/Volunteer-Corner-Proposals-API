package ua.nure.proposalservice.component.processor;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtProcessor {
    public String extractId(String token) {
        return Jwts.parser()
                .parseClaimsJws(token)
                .getBody().getId();
    }
    public String extractEmail(String token) {
        return Jwts.parser()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
