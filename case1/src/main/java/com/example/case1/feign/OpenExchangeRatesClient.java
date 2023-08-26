package com.example.case1.feign;

import com.example.case1.entity.CurrencyEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign-api-endpoints.openexchangerates.name}", url = "${feign-api-endpoints.openexchangerates.url}")
public interface OpenExchangeRatesClient {

    @GetMapping("/latest.json")
    CurrencyEntity getLatestCurrencyData(@RequestParam("app_id") String apiKey, @RequestParam(name = "base") String base);

    @GetMapping("/historical/{date}.json")
    CurrencyEntity getHistoricalCurrencyData(@PathVariable("date") String date, @RequestParam("app_id") String apiKey, @RequestParam(name = "base") String base);
}

