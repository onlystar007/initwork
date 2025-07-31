package com.example.demo;

import io.opentelemetry.api.trace.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/api/test")
    public String test() {
        Span span = Span.current();

        logger.info("今天天气真好{}{}", span.getSpanContext().getTraceId(), span.getSpanContext().getSpanId());
        return "1";
    }
}
