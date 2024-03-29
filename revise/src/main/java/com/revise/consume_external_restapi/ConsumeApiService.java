package com.revise.consume_external_restapi;

import com.revise.consume_external_restapi.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumeApiService {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(ConsumeApiService.class);

    public List<User> fetchUsersFromAOP() {
        String url = "http://localhost:8081/v1/user/";
        User[] users = this.restTemplate.getForObject(url, User[].class);
        List<User> list = Arrays.stream(users).collect(Collectors.toList());
        return list;
    }
}
