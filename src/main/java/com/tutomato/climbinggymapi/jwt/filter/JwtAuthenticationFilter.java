package com.tutomato.climbinggymapi.jwt.filter;

import com.tutomato.climbinggymapi.jwt.application.JwtService;
import com.tutomato.climbinggymapi.jwt.domain.JwtRule;
import com.tutomato.climbinggymapi.member.application.CustomMemberDetailService;
import com.tutomato.climbinggymapi.member.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomMemberDetailService memberDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (isPermittedURI(request.getRequestURI())) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtService.resolveTokenFromCookie(request, JwtRule.ACCESS_PREFIX);
        if (jwtService.validateAccessToken(accessToken)) {
            setAuthenticationToContext(accessToken);
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtService.resolveTokenFromCookie(request, JwtRule.REFRESH_PREFIX);
        Member member = findUserByRefreshToken(refreshToken);

        if (jwtService.validateRefreshToken(refreshToken, member.getIdentifier())) {
            String reissuedAccessToken = jwtService.generateAccessToken(response, member);
            jwtService.generateRefreshToken(response, member);

            setAuthenticationToContext(reissuedAccessToken);
            filterChain.doFilter(request, response);
            return;
        }

        jwtService.logout(user, response);
    }

    private boolean isPermittedURI(String requestURI) {
        return Arrays.stream(PERMITTED_URI)
                .anyMatch(permitted -> {
                    String replace = permitted.replace("*", "");
                    return requestURI.contains(replace) || replace.contains(requestURI);
                });
    }

    private User findUserByRefreshToken(String refreshToken) {
        String identifier = jwtService.getIdentifierFromRefresh(refreshToken);
        return userService.findUserByIdentifier(identifier);
    }

    private void setAuthenticationToContext(String accessToken) {
        Authentication authentication = jwtService.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
