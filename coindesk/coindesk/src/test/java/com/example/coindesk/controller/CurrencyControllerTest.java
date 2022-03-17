package com.example.coindesk.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.coindesk.CoindeskApplication;
import com.example.coindesk.service.CurrencyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoindeskApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyControllerTest {

	private MockMvc mockMvc;

	@Autowired
	CurrencyService currencyService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	public void testQryData(JSONObject jsonData, String tpye) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/currency")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData.toString())).andExpect(status().isOk())
				.andReturn();

		String resultDate = result.getResponse().getContentAsString();
		log.trace("{} = {}", tpye, resultDate);
	}

	public void testInsertData(JSONObject jsonData, String tpye) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/currency")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData.toString())).andExpect(status().isOk())
				.andReturn();

		String resultDate = result.getResponse().getContentAsString();
		log.trace("{} = {}", tpye, resultDate);
	}

	public void testUpdateData(JSONObject jsonData, String tpye) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/currency")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData.toString())).andExpect(status().isOk())
				.andReturn();

		String resultDate = result.getResponse().getContentAsString();
		log.trace("{} = {}", tpye, resultDate);
	}

	public void testDeleteData(JSONObject jsonData, String tpye) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/currency")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonData.toString())).andExpect(status().isOk())
				.andReturn();

		String resultDate = result.getResponse().getContentAsString();
		log.trace("{} = {}", tpye, resultDate);
	}

	@Test
	public void aTestQryDataCodeOk() {
		try {
			JSONObject jsonpost = new JSONObject();
			jsonpost.put("code", "USD");
			this.testQryData(jsonpost, "QryDataCodeOk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bTestInsertDataCodeOk() throws Exception {
		try {
			JSONObject jsonpost = new JSONObject();
			jsonpost.put("code", "USD");
			jsonpost.put("name_TW", "美金");
			this.testInsertData(jsonpost, "InsertDataCodeOk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void cTestUpdateDataCodeOk() throws Exception {
		try {
			JSONObject jsonpost = new JSONObject();
			jsonpost.put("code", "USD");
			jsonpost.put("name_TW", "日圓");
			this.testUpdateData(jsonpost, "UpdateDataCodeOk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void dTestDeleteDataCodeOk() throws Exception {
		try {
			JSONObject jsonpost = new JSONObject();
			jsonpost.put("code", "USD");
			this.testDeleteData(jsonpost, "DeleteDataCodeOk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callCoindeskApiCode(String url) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/currency/" + url))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String resultDate = result.getResponse().getContentAsString();
		log.trace("{} = {}", url, resultDate);
	}

	@Test
	public void eTestCallCoindeskApiCodeOk() {
		try {
			String url = "coindeskApi";
			this.callCoindeskApiCode(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void fTestCallNewCoindeskApiCodeOk() {
		try {
			String url = "coindeskDataConversion";
			this.bTestInsertDataCodeOk();
			this.callCoindeskApiCode(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
