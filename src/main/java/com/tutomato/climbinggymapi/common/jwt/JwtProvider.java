package com.tutomato.climbinggymapi.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtProvider.secretKey = secret;
    }
    private static String secretKey;

    private static final byte[] secret = secretKey.getBytes();
    private final Key key = Keys.hmacShaKeyFor(secret);



    /**
     * Token generate
     * (throw WeakKeyException when key length is ld 32byte)
     * */
    public String generateToken(Map<String, Object> claims, Date expiration ) {

        logger.info("====== CREATE TOKEN START {}, {} ======", claims, expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    /**
     * get claims for Token
     * (throw SignatureException when fail to valid signature)
     * (throw MalformedJwtException when jws not fit)
     * (throw ExpiredJwtException when token was expired)
     * */
    public Claims parseToken(String token) {

        logger.info("====== PARSE TOKEN TO CLAIM {}======", token);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Jwt createJwt(Map<String, Object> claims) {
        String accessToken = generateToken(claims, getExpireDateAccessToken());
        String refreshToken = generateToken(new HashMap<>(), getExpireDateRefreshToken());

        return new Jwt(accessToken, refreshToken);
    }


    /**
     * Token 정보에서 expired date 추출
     * */
    public Date getExpireDateAccessToken() {
        long expireTimeMils = 1000 * 60 * 60;
        return new Date(System.currentTimeMillis() + expireTimeMils);
    }

    /**
     * Token 정보에서 refresh token 추출
     * */
    public Date getExpireDateRefreshToken() {
        long expireTimeMils = 1000L * 60 * 60 * 24 * 60;
        return new Date(System.currentTimeMillis() + expireTimeMils);
    }
}
