package com.example.coindesk.model;

import java.util.Date;

import lombok.Data;

@Data
public class ResultModel<T> {
	MsgCode msgCode;
	String errorList;
	T Data = null;
	Date timestamp;

	public void setMsgCode(MsgCodeEnum msgCode) {
		this.msgCode = new MsgCode(msgCode);
	}

	public ResultModel() {
		this.timestamp = new Date();
	}

	public ResultModel(MsgCode _msgCode) {
		this();
		this.msgCode = _msgCode;
	}

	// 只傳DATA 判斷DATA是否為NULL
	public ResultModel(T data) {
		this(data, data != null ? MsgCodeEnum.SUCCESS : MsgCodeEnum.DATA_NOT_EXIST);
	}

	// 都傳
	public ResultModel(T data, MsgCodeEnum msgCode) {
		this();
		this.Data = data;
		this.msgCode = new MsgCode(msgCode);
	}

}
