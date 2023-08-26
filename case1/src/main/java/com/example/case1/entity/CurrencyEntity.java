package com.example.case1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
@Getter
@Setter
@ToString

public class CurrencyEntity {
    private String id;
    private String base;
    private Long timestamp;
    private String date;
    private Map<String, Double> rates;


}
