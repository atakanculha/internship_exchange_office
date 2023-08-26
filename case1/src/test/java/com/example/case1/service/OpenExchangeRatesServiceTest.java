package com.example.case1.service;

import com.example.case1.entity.CurrencyEntity;
import com.example.case1.feign.OpenExchangeRatesClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpenExchangeRatesServiceTest {

    @InjectMocks
    private OpenExchangeRatesService openExchangeRatesService;

    @Mock
    private OpenExchangeRatesClient openExchangeRatesClient;

    @Test
    public void testGetCurrencyData_WithValidDate() {
        String date = "2023-08-21";
        String base = "USD";
        CurrencyEntity currencyFromClient = new CurrencyEntity();
        currencyFromClient.setDate(date);

        when(openExchangeRatesClient.getHistoricalCurrencyData(date, null, base)).thenReturn(currencyFromClient);

        CurrencyEntity result = openExchangeRatesService.getCurrencyData(date, base);

        assertNotNull(result);
        assertEquals(date, result.getDate());
    }

    @Test
    public void testGetCurrencyData_WithLatestDate() {
        String base = "USD";
        CurrencyEntity currencyFromClient = new CurrencyEntity();

        when(openExchangeRatesClient.getLatestCurrencyData(null, base)).thenReturn(currencyFromClient);

        CurrencyEntity result = openExchangeRatesService.getCurrencyData(null, base);

        assertNotNull(result);
    }
}
