package com.example.coindesk.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Table(name = "Currency")
@Entity
@Data
public class CurrencyEntity {

	@Id
	@NotNull
	@NotEmpty(message = "幣別不得為空")
	@Length(max = 20, message = "長度不得超過20")
	String code; // 幣別代號 varchar(20)

	@Column
	@NotNull
	@Length(max = 20, message = "長度不得超過20")
	String name_TW; // 中文名稱 varchar(20)

}
