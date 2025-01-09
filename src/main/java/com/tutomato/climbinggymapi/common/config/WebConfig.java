package com.tutomato.climbinggymapi.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutomato.climbinggymapi.common.interceptor.JwtFilter;
import com.tutomato.climbinggymapi.common.interceptor.VerifyUserFilter;
import com.tutomato.climbinggymapi.common.jwt.JwtProvider;
import com.tutomato.climbinggymapi.member.service.MemberService;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean verifyUserFilter(ObjectMapper mapper, MemberService memberService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new VerifyUserFilter(mapper,memberService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/user/login");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jwtFilter(JwtProvider provider, ObjectMapper mapper, MemberService memberService) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter(provider,mapper,memberService));
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/user/login");
        return filterRegistrationBean;
    }
}
