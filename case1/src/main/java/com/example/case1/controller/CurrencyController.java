package com.example.case1.controller;

import com.example.case1.entity.CurrencyEntity;
import com.example.case1.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private static final String PATTERN_DATE_DASHED = "[0-9]{4,4}[-][0-9]{2,2}[-][0-9]{2,2}";
    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public CurrencyEntity getCurrencyValues( @RequestParam(required = false, name = "date") String date) {
        if (!isMatchedPattern(date)) {
            throw createArgumentException(date);
        }
        return currencyService.getCurrencyValues(date);

    }
    public boolean isMatchedPattern(String dateStr) {

        return dateStr == null || Pattern.matches(PATTERN_DATE_DASHED, dateStr);

    }
    public RuntimeException createArgumentException(String dateStr) {

        return new RuntimeException("date type is not valid. required date time is yyyy-MM-dd, given date value is " + dateStr);

    }
}
