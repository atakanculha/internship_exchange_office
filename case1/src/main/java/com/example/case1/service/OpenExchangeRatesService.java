package com.example.case1.service;

import com.example.case1.entity.CurrencyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenExchangeRatesService {
    @Autowired
    private com.example.case1.feign.OpenExchangeRatesClient openExchangeRatesClient;
    @Value("${feign-api-endpoints.openexchangerates.api-id}")
    private String apiKey;

    public CurrencyEntity getCurrencyData(String date, String base) {
        if (date == null) {
            return getLatestCurrencyData(base);
        }
        return getHistoricalCurrencyData(date, base);
    }
    private CurrencyEntity getLatestCurrencyData(String base) {
        return openExchangeRatesClient.getLatestCurrencyData(apiKey, base);
    }

    private CurrencyEntity getHistoricalCurrencyData(String date, String base) {
        return openExchangeRatesClient.getHistoricalCurrencyData(date, apiKey, base);
    }
}
