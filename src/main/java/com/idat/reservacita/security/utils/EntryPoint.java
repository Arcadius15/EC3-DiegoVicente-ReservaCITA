package com.idat.reservacita.security.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {
        try {
            log.warn(authException.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No esta autorizado");

    }
}