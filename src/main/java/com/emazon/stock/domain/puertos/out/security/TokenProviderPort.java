package com.emazon.stock.domain.puertos.out.security;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public interface TokenProviderPort {


    Object extractClaim(String token, String claimKey);
    String extractSubject(String token);

    Map<String, Object> extractAllClaims(String token);
    Date extractExpiration(String token);
    Boolean validateToken(String token, String subject);
    Boolean isTokenExpired(String token);

    Long extractUserId(String token);

    String extractRole(String token);
}
