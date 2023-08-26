package com.example.case1.service;

import com.example.case1.entity.CurrencyEntity;
import com.example.case1.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyService {
    private final static String CURRENCY_CODE = "USD";
    @Autowired
    private OpenExchangeRatesService currencyClient;
    @Autowired
    private CurrencyRepository currencyRepository;
    private Map<String, CurrencyEntity> cache = new HashMap<>();

    public CurrencyEntity getCurrencyValues(String date) {
        String dateAsAnID = generateCurrencyId(date);
        if (cache.containsKey(dateAsAnID)) {
                    return cache.get(dateAsAnID);
                }
        CurrencyEntity currencyEntity = getCurrencyFromDB(dateAsAnID);
        if (currencyEntity == null) {
            currencyEntity = getCurrencyFromClient(CURRENCY_CODE, dateAsAnID);
            if (currencyEntity != null) {
                persistToDB(currencyEntity, dateAsAnID);
                cache.put(dateAsAnID, currencyEntity);
            }
        }       else {
                    cache.put(dateAsAnID, currencyEntity);
        }
        return currencyEntity;
    }

    private static String generateCurrencyId(String date) {
        if (date != null){
            return date;
        }
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return formattedDate;
    }

    private CurrencyEntity getCurrencyFromClient(String currencyCode, String date) {
        return currencyClient.getCurrencyData(date, currencyCode);
    }

    private CurrencyEntity getCurrencyFromDB(String date) {
        return currencyRepository.findById(date).orElse(null);
    }

    public CurrencyEntity persistToDB(CurrencyEntity currency, String date) {
        currency.setId(date);
        return currencyRepository.save(currency);
    }

    public void setCache(Map<String, CurrencyEntity> cache) {
    }
}
