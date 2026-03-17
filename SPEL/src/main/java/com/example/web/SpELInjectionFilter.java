package com.example.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class SpELInjectionFilter implements Filter {

    // Regex pattern to detect potential SpEL injections
    private static final Pattern SPEL_INJECTION_PATTERN = Pattern.compile("#\\{[^}]+\\}");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if necessary
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // Check query parameters
            for (String param : httpRequest.getParameterMap().keySet()) {
                String[] values = httpRequest.getParameterValues(param);
                for (String value : values) {
                    if (isPotentialSpELInjection(value)) {
                        throw new ServletException("Potential SpEL injection detected in parameter: " + param);
                    }
                }
            }

            // Check headers
            Enumeration<String> headerNames = httpRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                String headerValue = httpRequest.getHeader(header);
                if (isPotentialSpELInjection(headerValue)) {
                    throw new ServletException("Potential SpEL injection detected in header: " + header);
                }
            }
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    }

    // Method to check if a value contains potential SpEL injection patterns
    private boolean isPotentialSpELInjection(String value) {
        return SPEL_INJECTION_PATTERN.matcher(value).find();
    }
}