//package com.example.demo.filter;
//
//import io.opentelemetry.api.trace.Span;
//import io.opentelemetry.api.trace.Tracer;
//import io.opentelemetry.context.Context;
//import io.opentelemetry.context.Scope;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.UUID;
//
//@Component
//public class TracingFilter implements Filter {
//
//    private final Tracer tracer;
//
//    public TracingFilter(Tracer tracer) {
//        this.tracer = tracer;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String path = httpRequest.getRequestURI();
//
//        // Create new span
//        Span span = tracer.spanBuilder("HTTP " + httpRequest.getMethod() + " " + path)
//                .startSpan();
//
//        try (Scope scope = span.makeCurrent()) {
//            // Set MDC context for logging
//            MDC.put("traceId", span.getSpanContext().getTraceId());
//            MDC.put("spanId", span.getSpanContext().getSpanId());
//
//            // Add request attributes
//            span.setAttribute("http.method", httpRequest.getMethod());
//            span.setAttribute("http.url", path);
//            span.setAttribute("http.client_ip", request.getRemoteAddr());
//
//            // Continue filter chain
//            chain.doFilter(request, response);
//
//            // Set response status
//            span.setAttribute("http.status_code", ((jakarta.servlet.http.HttpServletResponse) response).getStatus());
//        } finally {
//            // Clean up MDC
//            MDC.remove("traceId");
//            MDC.remove("spanId");
//            span.end();
//        }
//    }
//}