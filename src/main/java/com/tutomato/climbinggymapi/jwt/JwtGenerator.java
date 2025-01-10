package com.tutomato.climbinggymapi.jwt;

import com.tutomato.climbinggymapi.member.domain.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token을 생성하는 유틸 클래스
 *
 * */
@Component
public class JwtGenerator {

    public String generateAccessToken(
            final Key ACCESS_SECRET,
            final long ACCESS_EXPIRATION,
            Member member){

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setHeader(this.createHeader())
                .setClaims(this.createClaims(member))
                .setSubject(String.valueOf(member.getEmail()))
                .setExpiration(new Date(now + ACCESS_EXPIRATION))
                .signWith(ACCESS_SECRET, SignatureAlgorithm.HS256)
                .compact();
    }


    public String generateRefreshToken(
            final Key REFRESH_SECRET,
            final long REFRESH_EXPIRATION,
            Member member){

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setHeader(createHeader())
                .setSubject(member.getEmail())
                .setExpiration(new Date(now + REFRESH_EXPIRATION))
                .signWith(REFRESH_SECRET, SignatureAlgorithm.HS256)
                .compact();
    }


    private Map<String, Object> createHeader(){
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    private Map<String, Object> createClaims(Member member){
        Map<String, Object> claims = new HashMap<>();
        claims.put("Identifier", member.getEmail());
        claims.put("Role", member.getRole());
        return claims;


    }



}
