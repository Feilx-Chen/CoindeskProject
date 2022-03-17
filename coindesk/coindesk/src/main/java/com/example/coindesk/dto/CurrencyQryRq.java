package com.example.coindesk.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class CurrencyQryRq {
	@NotNull(message="code 必須存在")
	@Length(max = 20, message = "code長度不得超過20")
	String code;  
}
