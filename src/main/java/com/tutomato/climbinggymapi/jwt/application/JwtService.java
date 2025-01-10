package com.tutomato.climbinggymapi.jwt.application;

import com.tutomato.climbinggymapi.common.exception.InvalidTokenException;
import com.tutomato.climbinggymapi.jwt.JwtGenerator;
import com.tutomato.climbinggymapi.jwt.domain.JwtRule;
import com.tutomato.climbinggymapi.jwt.domain.Token;
import com.tutomato.climbinggymapi.jwt.domain.TokenStatus;
import com.tutomato.climbinggymapi.jwt.repository.TokenRepository;
import com.tutomato.climbinggymapi.member.application.CustomMemberDetailService;
import com.tutomato.climbinggymapi.member.domain.Member;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class JwtService {

    private final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private final CustomMemberDetailService customMemberDetailService;
    private final JwtGenerator jwtGenerator;
    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;
    private final Key ACCESS_SECRET_KEY;
    private final Key REFRESH_SECRET_KEY;
    private final long ACCESS_EXPIRATION;
    private final long REFRESH_EXPIRATION;

    public JwtService(
            CustomMemberDetailService customMemberDetailService,
            JwtGenerator jwtGenerator,
            TokenRepository tokenRepository,
            JwtUtil jwtUtil,
            @Value("${jwt.test.access-secret}") String ACCESS_SECRET_KEY,
            @Value("${jwt.test.refresh-secret}") String ACCESS_REFRESH_KEY,
            @Value("${jwt.test.access-expiration}") long ACCESS_EXPIRATION,
            @Value("${jwt.test.refresh-expiration}") long REFRESH_EXPIRATION) {

        this.customMemberDetailService = customMemberDetailService;
        this.jwtGenerator = jwtGenerator;
        this.tokenRepository = tokenRepository;
        this.jwtUtil = jwtUtil;
        this.ACCESS_SECRET_KEY = jwtUtil.getSigningKey(ACCESS_SECRET_KEY);
        this.REFRESH_SECRET_KEY = jwtUtil.getSigningKey(ACCESS_REFRESH_KEY);
        this.ACCESS_EXPIRATION = ACCESS_EXPIRATION;
        this.REFRESH_EXPIRATION = REFRESH_EXPIRATION;
    }

    /**
     * 새로운 Access Token을 생성하여
     * 응답 Header에 등록합니다.
     * */
    public String generateAccessToken(HttpServletResponse response, Member requestMember) {
        String accessToken = jwtGenerator.generateAccessToken(ACCESS_SECRET_KEY, ACCESS_EXPIRATION, requestMember);
        ResponseCookie cookie = setTokenToCookie(JwtRule.ACCESS_PREFIX.getValue(), accessToken, ACCESS_EXPIRATION / 1000);
        response.addHeader(JwtRule.JWT_ISSUE_HEADER.getValue(), cookie.toString());

        return accessToken;
    }

    /**
     * 새로운 Refresh Token을 생성하여
     * 응답 Header에 등록하고 내용을 저장합니다.
     * */
    public String generateRefreshToken(HttpServletResponse response, Member requestMember) {

        String refreshToken = jwtGenerator.generateRefreshToken(REFRESH_SECRET_KEY, REFRESH_EXPIRATION, requestMember);
        ResponseCookie cookie = setTokenToCookie(JwtRule.REFRESH_PREFIX.getValue(), refreshToken, REFRESH_EXPIRATION / 1000);
        response.addHeader(JwtRule.JWT_ISSUE_HEADER.getValue(), cookie.toString());

        //TODO insert token repository
        tokenRepository.save(new Token(requestMember.getIdentifier(), refreshToken));

        return refreshToken;

    }

    /**
     * Cookie에 포함되어 전달된 Token값을 사용가능한 값으로 변환합니다.
     * */
    public String resolveTokenFromCookie(HttpServletRequest request, JwtRule tokenPrefix) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)) {
            logger.error("[JwtService@resolveTokenFromCookie] Request Cookie is empty");
            throw new InvalidTokenException("유효하지 않은 요청입니다. 확인 후 다시 시도해주세요.");
        }

        return jwtUtil.resolveTokenFromCookie(cookies, tokenPrefix);

    }

    /**
    * access token 유효성 검증
    * */
    public boolean validateAccessToken(String accessToken) {
        return jwtUtil.getTokenStatus(accessToken, ACCESS_SECRET_KEY).equals(TokenStatus.AUTHENTICATED);
    }

    /**
     * refresh token 유효성, 일치여부 검증
     * */
    public boolean validateRefreshToken(String refreshToken, String identifier) {
        boolean isRefreshValid = jwtUtil.getTokenStatus(refreshToken, REFRESH_SECRET_KEY).equals(TokenStatus.AUTHENTICATED);

        Token storedToken = tokenRepository.findByIdentifier(identifier);
        boolean isTokenMatched = storedToken.getRefreshToken().equals(refreshToken);

        return isRefreshValid && isTokenMatched;
    }

    /**
     * Spring security context holder에 Authentication 객체 저장
     * */
    public Authentication getAuthentication(String token) {
        UserDetails principal = customMemberDetailService.loadUserByUsername(getUserPk(token, ACCESS_SECRET_KEY));
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());

    }

    public String getUserPk(String token, Key secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    private ResponseCookie setTokenToCookie(String tokenPrefix, String token, long maxAgeSeconds) {
        return ResponseCookie.from(tokenPrefix, token)
                .path("/")
                .maxAge(maxAgeSeconds)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .build();
    }





}
