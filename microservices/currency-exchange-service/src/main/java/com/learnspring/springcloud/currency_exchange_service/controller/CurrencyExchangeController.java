package com.learnspring.springcloud.currency_exchange_service.controller;

import com.learnspring.springcloud.currency_exchange_service.model.CurrencyExchange;
import com.learnspring.springcloud.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to
    ) {
        CurrencyExchange currencyExchange = this.currencyExchangeRepository.findByFromAndTo(from, to);
        String port = this.environment.getProperty("local.server.port");
        if (currencyExchange == null)
            throw new RuntimeException("Unable to find from " + from + " to " + to + ".");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}