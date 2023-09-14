package com.revise.consume_external_restapi;

import com.revise.consume_external_restapi.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consume")
public class ConsumeApiController {

    @Autowired
    private ConsumeApiService consumeApiService;

    @GetMapping("/")
    public ResponseEntity<?> getData() {
        List<User> users = this.consumeApiService.fetchUsersFromAOP();
        return ResponseEntity.ok(null);
    }
}
