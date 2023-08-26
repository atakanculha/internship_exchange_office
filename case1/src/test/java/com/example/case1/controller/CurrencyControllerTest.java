package com.example.case1.controller;

import com.example.case1.entity.CurrencyEntity;
import com.example.case1.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyControllerTest {

    @Mock
    private CurrencyService currencyService;

    @InjectMocks
    private CurrencyController currencyController;

    @Test
    public void testGetCurrencyValues_ValidDate() {
        String validDate = "2023-08-21";
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setDate(validDate);

        when(currencyService.getCurrencyValues(validDate)).thenReturn(currencyEntity);

        CurrencyEntity response = currencyController.getCurrencyValues(validDate);

        assertNotNull(response);
    }

    @Test
    public void testGetCurrencyValues_InvalidDate() {
        String invalidDate = "invalid-date";

        assertThrows(RuntimeException.class, () -> currencyController.getCurrencyValues(invalidDate));

    }
}
