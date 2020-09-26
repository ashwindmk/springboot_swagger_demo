package com.ashwin.java.api.controller;

import com.ashwin.java.domain.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    @Autowired
    private ApplicationContext context;

    @GetMapping
    public ResponseEntity<Quote> quote() {
        Quote quote = context.getBean(Quote.class);
        System.out.println("Quote: " + quote);
        return ResponseEntity.ok(quote);
    }
}
