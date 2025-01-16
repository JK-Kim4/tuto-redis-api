package com.tutomato.coreapi.jwt.application;

import com.tutomato.climbinggymapi.jwt.domain.JwtRule;
import com.tutomato.climbinggymapi.jwt.domain.TokenStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtUtil {

    private final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public TokenStatus getTokenStatus(String token, Key secretKey) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return TokenStatus.AUTHENTICATED;
        }catch (ExpiredJwtException | IllegalArgumentException e) {
            //토큰과 시크릿 키의 정보는 유효하나 기간이 만료됨
            logger.error("유효기간이 만료된 토큰입니다.");
            return TokenStatus.EXPIRED;
        }catch (JwtException e){
            //토근과 시크릿 정보가 요휴하지않음
            logger.error("토큰 혹은 시크릿 정보가 유효하지않습니다.");
            return TokenStatus.INVALID;
        }
    }


    public String resolveTokenFromCookie(Cookie[] cookies, JwtRule tokenPrefix){
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(tokenPrefix.getValue()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse("token is null");
    }

    public Key getSigningKey(String secretKey){
        String encodedKey = encodeToBase64(secretKey);
        return Keys.hmacShaKeyFor(encodedKey.getBytes(StandardCharsets.UTF_8));
    }

    public String encodeToBase64(String secretKey){
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Cookie resetToken(JwtRule tokenPrefix){
        Cookie cookie = new Cookie(tokenPrefix.getValue(), null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;

    }

}
