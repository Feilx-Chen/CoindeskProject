package com.example.coindesk.config.po;

import lombok.Data;

@Data
public class CurrencyNamePO {
	String code;
	String symbol;
	String rate;
	String description;
	String rate_float;
}
