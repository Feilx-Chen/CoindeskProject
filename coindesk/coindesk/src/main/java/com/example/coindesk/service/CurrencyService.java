package com.example.coindesk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coindesk.config.po.CoindeskPO;
import com.example.coindesk.config.po.CurrencyNamePO;
import com.example.coindesk.config.po.NewCoindeskPO;
import com.example.coindesk.config.po.NewCurrencyNamePO;
import com.example.coindesk.dao.CurrencyDao;
import com.example.coindesk.dto.CurrencyQryRq;
import com.example.coindesk.entity.CurrencyEntity;
import com.example.coindesk.model.MsgCode;
import com.example.coindesk.model.MsgCodeEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrencyService {

	@Autowired
	CurrencyDao currencyDao;

	@Autowired
	RestService restService;

	// 用code 查詢 Currency
	public CurrencyEntity queryCurrencyByCode(String code) {
		log.trace("queryCurrencyByCode");
		Optional<CurrencyEntity> rs = currencyDao.findById(code);
		return rs.isPresent() ? rs.get() : null;
	}

	public CurrencyEntity queryCurrencyByCode(CurrencyQryRq rq) {
		return queryCurrencyByCode(rq.getCode());
	}

	// 新增 Currency
	public MsgCode insertCurrency(CurrencyEntity rq) {
		log.trace("insertCurrency");
		CurrencyEntity currency = queryCurrencyByCode(rq.getCode());
		if (currency != null) {
			// 資料已存在
			return new MsgCode(MsgCodeEnum.DATA_ALREADY_EXIST);
		} else {
			currencyDao.save(rq);
			MsgCode mg = new MsgCode(MsgCodeEnum.SUCCESS);
			return mg;
		}
	}

	// 更新 Currency
	public CurrencyEntity updateCurrency(CurrencyEntity rq) {
		log.trace("updateCurrency");
		CurrencyEntity currency = queryCurrencyByCode(rq.getCode());
		if (currency == null) {
			// 資料不存在
			return null;
		} else {
			currencyDao.save(rq);
			return queryCurrencyByCode(rq.getCode());
		}
	}

	// 用code 刪除 Currency
	public MsgCode deleteCurrencyByCode(String code) {
		log.trace("deleteCurrencyByCode");
		CurrencyEntity currency = queryCurrencyByCode(code);
		if (currency == null) {
			// 資料不存在
			return new MsgCode(MsgCodeEnum.DATA_NOT_EXIST);
		} else {
			currencyDao.deleteById(code);
			MsgCode mg = new MsgCode(MsgCodeEnum.SUCCESS);
			return mg;
		}
	}

	// 刪除 Currency
	public MsgCode deleteCurrency(CurrencyQryRq rq) {
		return deleteCurrencyByCode(rq.getCode());
	}

	// 打CoindeskApi
	public CoindeskPO getCoindeskApi() {
		String Url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		return restService.callApiUseGet(Url, CoindeskPO.class);
	}

	// 資料轉換，組新API
	public NewCoindeskPO dataConversion() {
		CoindeskPO coindeskPO = getCoindeskApi();
		NewCoindeskPO newCoindeskPO = null;
		NewCurrencyNamePO newCurrencyNamePO = null;
		Map<String, NewCurrencyNamePO> bpi = new HashMap<String, NewCurrencyNamePO>();
		if (coindeskPO != null) {
			newCoindeskPO = new NewCoindeskPO();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String updatedISO = coindeskPO.getTime().getUpdatedISO();
			String dataString = "";
			try {
				Date date = df.parse(updatedISO);
				dataString = df2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			for (Map.Entry<String, CurrencyNamePO> entry : coindeskPO.getBpi().entrySet()) {
				newCurrencyNamePO = new NewCurrencyNamePO();
				CurrencyNamePO v = entry.getValue();
				CurrencyEntity ce = queryCurrencyByCode(v.getCode());
				newCurrencyNamePO.setCode(v.getCode());
				newCurrencyNamePO.setRate(v.getRate());
				newCurrencyNamePO.setName_TW(ce != null ? ce.getName_TW() : "");
				bpi.put(v.getCode(), newCurrencyNamePO);
			}
			newCoindeskPO.setUpdatedISO(dataString);
			newCoindeskPO.setBpi(bpi);
		}
		return newCoindeskPO;
	}
}
