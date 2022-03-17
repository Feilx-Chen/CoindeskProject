package com.example.coindesk.model;

public enum MsgCodeEnum {
	SUCCESS(0, "成功"), 
	DATA_NOT_EXIST(1, "資料不存在。"), 
	INPUT_NOT_VALID(2, "輸入驗證錯誤。"), 
	EXCEPTION(3, "例外錯誤。"),
	DATA_ALREADY_EXIST(4, "資料已存在，無法新增。");

	final int code;
	final String desc;

	MsgCodeEnum(int _code, String _desc) {
		this.code = _code;
		this.desc = _desc;
	}
}
