package com.ekang.studyroom.config.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Component
public class JwtTokenProvider implements AuthTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String SECRET_KEY = "SOME_SECRET_KEY";
    private static final long EXPIRATION_MS = 1000 * 60 * 10; // valid for 10 min

    @Override
    public String parseTokenString(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    public JwtAuthToken issue(int userId) {
        return JwtAuthToken.builder().token(buildToken(userId)).build();
    }

    // generate Jwt token
    private String buildToken(int userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plus(EXPIRATION_MS, ChronoUnit.MILLIS);
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expired.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    @Override
    public Long getTokenOwnerId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    @Override
    public boolean validToken(String token) {
        if (StringUtils.hasLength(token)) {
            try {
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            } catch (SignatureException e) {
                logger.error("Invalid JWT signature", e);
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token", e);
            } catch (ExpiredJwtException e) {
                logger.error("Expired JWT token", e);
            } catch (UnsupportedJwtException e) {
                logger.error("Unsupported JWT token", e);
            } catch (IllegalArgumentException e) {
                logger.error("JWT claims string is empty", e);
            }
        }

        return false;
    }
}
