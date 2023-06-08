package com.bosonit.formacion.ejerciciocontroller;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RedirigeFilter extends OncePerRequestFilter {


    private static final String REDIRECT_URL = "/salta";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String redirigeHeader = request.getHeader("REDIRIGE");
        String requestUrl = request.getRequestURI();

        if (redirigeHeader != null && redirigeHeader.equalsIgnoreCase("SALTA")) {
            if (!requestUrl.equals(REDIRECT_URL)) {
                response.sendRedirect(REDIRECT_URL);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}