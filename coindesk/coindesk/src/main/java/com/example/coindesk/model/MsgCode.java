package com.example.coindesk.model;

public class MsgCode {
	private final int code;
	private final String desc;
	private String error;

	public MsgCode(MsgCodeEnum msgCodeEnum) {

		this.code = msgCodeEnum.code;
		this.desc = msgCodeEnum.desc;
	}

	public void setError(String _error) {
		this.error = _error;
	}
}
