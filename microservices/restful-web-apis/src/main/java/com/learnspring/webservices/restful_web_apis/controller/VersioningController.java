package com.learnspring.webservices.restful_web_apis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class VersioningController {

    @GetMapping(path = "/person", params = "v1=1")
    public ResponseEntity<Object> getPersonVersion1() {
        return new ResponseEntity<>("Krishna Lagad", HttpStatus.OK);
    }

    @GetMapping(path = "/person", params = "v2=1")
    public ResponseEntity<Object> getPersonVersion2() {
        return new ResponseEntity<>(new HashMap<String, String>() {{
            put("firstName", "krishna");
            put("lastName", "Lagad");
        }}, HttpStatus.OK);
    }

    @GetMapping(path = "/person/header", headers = "X-API_VERSION=1")
    public ResponseEntity<Object> getPersonVersion1Header() {
        return new ResponseEntity<>("Krishna Lagad", HttpStatus.OK);
    }

    @GetMapping(path = "/person/header", headers = "X-API_VERSION=2")
    public ResponseEntity<Object> getPersonVersion2Header() {
        return new ResponseEntity<>(new HashMap<String, String>() {{
            put("firstName", "krishna");
            put("lastName", "Lagad");
        }}, HttpStatus.OK);
    }

    @GetMapping(path = "/person/accept", produces = "application/dnd.company.app-v1+json")
    public ResponseEntity<Object> getPersonVersion1Accept() {
        return new ResponseEntity<>("Krishna Lagad", HttpStatus.OK);
    }

    @GetMapping(path = "/person/accept", produces = "application/dnd.company.app-v2+json")
    public ResponseEntity<Object> getPersonVersion2Accept() {
        return new ResponseEntity<>(new HashMap<String, String>() {{
            put("firstName", "krishna");
            put("lastName", "Lagad");
        }}, HttpStatus.OK);
    }
}