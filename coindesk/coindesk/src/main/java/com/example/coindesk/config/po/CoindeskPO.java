package com.example.coindesk.config.po;

import java.util.Map;

import lombok.Data;

@Data
public class CoindeskPO {
	TimePO time;
	String disclaimer;
	String chartName;
	Map<String, CurrencyNamePO> bpi;
}
