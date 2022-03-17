package com.example.coindesk.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.coindesk.entity.CurrencyEntity;

// 第一個參數為訪問的實體，第二參數是這個Entity ID的資料型態
public interface CurrencyDao extends CrudRepository<CurrencyEntity, String> {

}
