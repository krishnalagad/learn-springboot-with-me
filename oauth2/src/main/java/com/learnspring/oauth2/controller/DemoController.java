package com.learnspring.oauth2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/public")
    public ResponseEntity<String> publicApi() {
        return ResponseEntity.ok("This is a public Api");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateApi(OAuth2AuthenticationToken token) {
        return ResponseEntity.ok("Authenticated: " + token.getPrincipal().getAttribute("email"));
    }
}