package com.example.case1.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyEntityTest {

    @Test
    public void testGetterSetter() {


        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId("1");
        currencyEntity.setBase("USD");
        currencyEntity.setDate("2023-08-21");


        String id = currencyEntity.getId();
        String base = currencyEntity.getBase();
        String date = currencyEntity.getDate();


        assertEquals("1", id);
        assertEquals("USD", base);
        assertEquals("2023-08-21", date);
    }

    @Test
    public void testToString() {

        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId("1");
        currencyEntity.setBase("USD");
        currencyEntity.setDate("2023-08-21");


        String toStringResult = currencyEntity.toString();


        String expectedToString = "CurrencyEntity(id=1, base=USD, timestamp=null, date=2023-08-21, rates=null)";
        assertEquals(expectedToString, toStringResult);
    }
}
