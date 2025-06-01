package com.learnspring.mcp;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeService {
    private final Logger logger = LoggerFactory.getLogger(BikeService.class);
    private List<Bike> bikes = new ArrayList<>();

    @Tool(name = "get_all_bikes", description = "Get all the bikes from the collection")
    public List<Bike> getBikes() {
        return bikes;
    }

    @Tool(name = "get_bike", description = "Get single bike from the collection by its name")
    public Bike getBike(String name) {
        return bikes.stream().filter(bike -> bike.name().equals(name)).findFirst().orElse(null);
    }

    @PostConstruct
    public void init() {
        bikes.addAll(List.of(
                new Bike("Apache RTR160 4v", "The TVS Apache RTR 160 4V is a popular naked sports bike in the" +
                        " 160cc segment in India, known for its performance, aggressive styling, and race-derived engine."),
                new Bike("Pulsar N160", "The Bajaj Pulsar N160 is a prominent offering in the 160cc naked " +
                        "street fighter segment, designed to appeal to riders seeking a blend of sporty performance, modern " +
                        "features, and urban practicality. "),
                new Bike("Honda CB350", "The Honda CB350 refers to a family of retro-styled motorcycles from " +
                        "Honda, specifically designed for the Indian market and sold through their premium BigWing dealerships.")
        ));
    }

}