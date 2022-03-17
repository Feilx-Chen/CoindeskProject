package com.example.coindesk.config.po;

import java.util.Map;

import lombok.Data;

@Data
public class NewCoindeskPO {
	String updatedISO;
	Map<String, NewCurrencyNamePO> bpi;
}
