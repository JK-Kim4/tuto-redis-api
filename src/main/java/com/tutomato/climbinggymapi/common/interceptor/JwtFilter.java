package com.tutomato.climbinggymapi.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutomato.climbinggymapi.common.jwt.Jwt;
import com.tutomato.climbinggymapi.common.jwt.JwtProvider;
import com.tutomato.climbinggymapi.member.domain.AuthenticateMember;
import com.tutomato.climbinggymapi.member.service.MemberService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper;

    private final MemberService memberService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        Object attribute = request.getAttribute(VerifyUserFilter.AUTHENTICATE_MEMBER);

        if (attribute instanceof AuthenticateMember authenticateUser) {
            Map<String, Object> claims = new HashMap<>();
            String authenticateUserJson = objectMapper.writeValueAsString(authenticateUser);
            claims.put(VerifyUserFilter.AUTHENTICATE_MEMBER, authenticateUserJson);
            Jwt jwt = jwtProvider.createJwt(claims);
            memberService.updateRefreshToken(authenticateUser.getEmail(), jwt.getRefreshToken());
            String json = objectMapper.writeValueAsString(jwt);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
