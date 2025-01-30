package com.learnspring.springclod.currency_conversion_service.controller;

import com.learnspring.springclod.currency_conversion_service.model.CurrencyConversion;
import com.learnspring.springclod.currency_conversion_service.model.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrency(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer quantity
    ) {
        Map<String, String> uriVariables = new HashMap<>() {{
            put("from", from);
            put("to", to);
        }};
        ResponseEntity<CurrencyConversion> forEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        uriVariables);

        CurrencyConversion currencyConversion = forEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(), from, to,
                BigDecimal.valueOf(quantity),
                currencyConversion.getConversionMultiple(),
                BigDecimal.valueOf(quantity).multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " RestTemplate"
        );
    }

    // using feign dependency
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrencyFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer quantity
    ) {
        CurrencyConversion currencyConversion = this.currencyExchangeProxy.retrieveExchangeValue(from, to);

        return new CurrencyConversion(currencyConversion.getId(), from, to,
                BigDecimal.valueOf(quantity),
                currencyConversion.getConversionMultiple(),
                BigDecimal.valueOf(quantity).multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " Feign"
        );
    }
}