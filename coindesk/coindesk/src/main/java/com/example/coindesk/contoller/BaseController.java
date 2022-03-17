package com.example.coindesk.contoller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.coindesk.exception.ParamException;
import com.example.coindesk.model.MsgCode;
import com.example.coindesk.model.MsgCodeEnum;
import com.example.coindesk.model.ResultModel;

public class BaseController {
	// 處理例外狀況

	@ExceptionHandler(value = Exception.class)
	public ResultModel handleException(Exception e) {

		if (e instanceof ParamException) {
			ResultModel<Object> rs = new ResultModel<Object>();
			rs.setMsgCode(MsgCodeEnum.INPUT_NOT_VALID);
			rs.setErrorList(e.getMessage());
			return rs;
		}
		MsgCode msg = new MsgCode(MsgCodeEnum.EXCEPTION);
		msg.setError(e.getMessage());
		return new ResultModel(msg);

	}
}
