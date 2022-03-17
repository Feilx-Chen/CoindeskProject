package com.example.coindesk.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coindesk.config.po.CoindeskPO;
import com.example.coindesk.config.po.NewCoindeskPO;
import com.example.coindesk.dto.CurrencyQryRq;
import com.example.coindesk.entity.CurrencyEntity;
import com.example.coindesk.model.MsgCode;
import com.example.coindesk.model.ResultModel;
import com.example.coindesk.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "/currency")
public class CurrencyController extends BaseController {

	@Autowired
	CurrencyService CurrencySvc;

	// 查詢
	@GetMapping()
	public ResultModel<CurrencyEntity> doGet(@RequestBody @Validated CurrencyQryRq request,
			BindingResult bindingResult) {
		log.trace("doGet = {}", request);
		CurrencyEntity ce = CurrencySvc.queryCurrencyByCode(request);
		ResultModel<CurrencyEntity> resultModel = new ResultModel<CurrencyEntity>(ce);
		return resultModel;
	}

	// 新增
	@PostMapping()
	public ResultModel<CurrencyEntity> doPost(@RequestBody @Validated CurrencyEntity request,
			BindingResult bindingResult) {
		log.trace("doPost = {}", request);
		MsgCode msg = CurrencySvc.insertCurrency(request);
		return new ResultModel<CurrencyEntity>(msg);
	}

	// 更新
	@PutMapping()
	public ResultModel<CurrencyEntity> doPut(@RequestBody @Validated CurrencyEntity request,
			BindingResult bindingResult) {
		log.trace("doPut = {}", request);
		CurrencyEntity ce = CurrencySvc.updateCurrency(request);
		ResultModel<CurrencyEntity> resultModel = new ResultModel<CurrencyEntity>(ce);
		return resultModel;
	}

	// 刪除
	@DeleteMapping()
	public ResultModel<CurrencyEntity> doDelete(@RequestBody @Validated CurrencyQryRq request,
			BindingResult bindingResult) {
		log.trace("doDelete = {}", request);
		MsgCode msg = CurrencySvc.deleteCurrency(request);
		return new ResultModel<CurrencyEntity>(msg);
	}

	// 取得CoindeskApi
	@GetMapping(value = "/coindeskApi")
	public ResultModel getCoindeskApi() {
		log.trace("getCoindeskApi");
		CoindeskPO coindeskData = CurrencySvc.getCoindeskApi();
		return new ResultModel(coindeskData);
	}

	// 轉換CoindeskApi資料
	@GetMapping(value = "/coindeskDataConversion")
	public ResultModel coindeskDataConversion() {
		log.trace("coindeskDataConversion");
		NewCoindeskPO newCoindeskData = CurrencySvc.dataConversion();
		return new ResultModel(newCoindeskData);
	}
}