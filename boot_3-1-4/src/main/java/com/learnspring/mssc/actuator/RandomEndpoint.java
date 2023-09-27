package com.learnspring.mssc.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Service;

@Service
@Endpoint(id = "random")
public class RandomEndpoint {

    @ReadOperation
    public int random() {
        return (int) (Math.random() * 100);
    }
}
