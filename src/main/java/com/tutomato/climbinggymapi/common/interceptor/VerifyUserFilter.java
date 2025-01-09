package com.tutomato.climbinggymapi.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutomato.climbinggymapi.common.exception.MemberNotFoundException;
import com.tutomato.climbinggymapi.member.api.dto.MemberLoginDto;
import com.tutomato.climbinggymapi.member.api.dto.MemberResponseDto;
import com.tutomato.climbinggymapi.member.domain.AuthenticateMember;
import com.tutomato.climbinggymapi.member.domain.Role;
import com.tutomato.climbinggymapi.member.service.MemberService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class VerifyUserFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(VerifyUserFilter.class);
    public static final String AUTHENTICATE_MEMBER = "authenticateMember";
    private final ObjectMapper objectMapper;

    private final MemberService memberService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if ((httpServletRequest.getMethod().equals(HttpMethod.POST.name()))) {
            try {
                MemberLoginDto memberLoginDto = objectMapper.readValue(request.getReader(), MemberLoginDto.class);
                MemberResponseDto member = memberService.findByEmail(memberLoginDto.getEmail());

                if(Objects.nonNull(member)) {
                    Set<Role> memberRoles = memberService.getMemberRolesByEmail(member.getEmail());
                    request.setAttribute(AUTHENTICATE_MEMBER, new AuthenticateMember(member.getEmail(),memberRoles));
                }else {
                    throw new MemberNotFoundException("일치하는 회원 정보가 존재하지않습니다.");
                }
                chain.doFilter(request, response);
            } catch (Exception e) {
                logger.error("Fail User Verify");
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
}
