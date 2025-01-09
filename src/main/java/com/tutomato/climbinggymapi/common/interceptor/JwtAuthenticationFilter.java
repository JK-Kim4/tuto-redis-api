package com.tutomato.climbinggymapi.common.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutomato.climbinggymapi.common.exception.AuthorizationException;
import com.tutomato.climbinggymapi.common.jwt.JwtProvider;
import com.tutomato.climbinggymapi.member.domain.AuthenticateMember;
import com.tutomato.climbinggymapi.member.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String[] whiteListUris = new String[]{"/user/login","/auth/refresh/token","/user/register","*/h2-console*"};

    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if(whiteListCheck(httpServletRequest.getRequestURI())){
            chain.doFilter(request, response);
            return;
        }
        if(!isContainToken(httpServletRequest)){
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"인증 오류");
            return;
        }
        try{
            String token = getToken(httpServletRequest);
            AuthenticateMember authenticateMember = getAuthenticateMember(token);
            verifyAuthorization(httpServletRequest.getRequestURI(),authenticateMember);
            logger.info("값 : {}",authenticateMember.getEmail());
            chain.doFilter(request, response);
        } catch (JsonParseException e){
            logger.error("JsonParseException");
            httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException e){
            logger.error("JwtException");
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "인증 오류");
        } catch (ExpiredJwtException e){
            logger.error("JwtTokenExpired");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "토큰이 만료 되었습니다");
        } catch (AuthorizationException e){
            logger.error("AuthorizationException");
            httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "권한이 없습니다");
        }
    }

    private boolean whiteListCheck(String uri){
        return PatternMatchUtils.simpleMatch(whiteListUris,uri);
    }

    private boolean isContainToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return authorization != null && authorization.startsWith("Bearer ");
    }

    private String getToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return authorization.substring(7);
    }

    private AuthenticateMember getAuthenticateMember(String token) throws JsonProcessingException {
        Claims claims = jwtProvider.parseToken(token);
        String authenticateUserJson = claims.get(VerifyUserFilter.AUTHENTICATE_MEMBER, String.class);
        return objectMapper.readValue(authenticateUserJson, AuthenticateMember.class);
    }

    private void verifyAuthorization(String uri, AuthenticateMember member){
        if(PatternMatchUtils.simpleMatch("*/admin*",uri) && !member.getRoles().contains(Role.ADMIN)){
            throw new AuthorizationException();
        }
    }

}
