package com.example.coindesk.service;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class RestService {

	public static <T> T callApiUseGet(String Url, Class<T> T) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet get = new HttpGet(Url);
		CloseableHttpResponse response;
		try {
			response = httpclient.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				T rs = new Gson().fromJson(EntityUtils.toString(response.getEntity()), T);
				return rs;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
