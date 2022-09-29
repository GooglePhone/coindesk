package my.company.coin;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.company.coin.controller.IndexController;
import my.company.coin.entity.Coin;
import my.company.coin.service.CoinService;

@Configuration
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CoinApplication.class)
class CurrencyApplicationTests {
	ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private IndexController indexController;
    @Autowired
    private CoinService coinService;
  
    
    /**
     * 測試呼叫查詢幣別對應表資料 API，並顯示其內容
     */
	@Test
	void step1(){
		System.out.println("測試呼叫查詢幣別對應表資料 API，並顯示其內容");
		System.out.println(indexController.initData());
	}
    
	/**
	 * 測試呼叫新增幣別對應表資料 API
	 * @throws JSONException 
	 * @throws JsonProcessingException 
	 */
	@Test
	void step2() throws JSONException, JsonProcessingException {
		String s = "{\"time\":{\"updated\":\"Sep 27, 2022 11:18:00 UTC\",\"updatedISO\":\"2022-09-27T11:18:00+00:00\",\"updateduk\":\"Sep 27, 2022 at 12:18 BST\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"20,181.0550\",\"description\":\"United States Dollar\",\"rate_float\":20181.055},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"16,863.1281\",\"description\":\"British Pound Sterling\",\"rate_float\":16863.1281},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"19,659.2940\",\"description\":\"Euro\",\"rate_float\":19659.294}}}";
		System.out.println("測試呼叫新增幣別對應表資料 API");
		System.out.println(objectMapper.writeValueAsString(coinService.saveData(new JSONObject(s))));
		System.out.println(indexController.insertNewCoin(new JSONObject(s)));
	}
	/**
	 * 測試呼叫更新幣別對應表資料 API，並顯示其內容
	 * @throws JsonProcessingException 
	 */
	@Test
	void step3() throws JsonProcessingException{
		Coin coin = coinService.findAll().get(0);
		coin.setChartName("fffggasdasdsad");
		System.out.println("測試呼叫更新幣別對應表資料 API，並顯示其內容");
		System.out.println(objectMapper.writeValueAsString(coinService.updateCoin(coin)));
		System.out.println(indexController.updateNewCoin(coin));
	}
	/**
	 * 測試呼叫刪除幣別對應表資料 API
	 */
	@Test
	void step4(){
		System.out.println("測試呼叫刪除幣別對應表資料 API");
		System.out.println(indexController.deleteNewCoin(1));
	}
	/**
	 * 測試呼叫 coindesk API，並顯示其內容。
	 */
	@Test
	void step5(){
		System.out.println("測試呼叫 coindesk API，並顯示其內容。");
		System.out.println(indexController.getCoinDesk());
	}
	/**
	 * 測試呼叫資料轉換的 API，並顯示其內容
	 */
	@Test
	void step6(){
		System.out.println("測試呼叫資料轉換的 API，並顯示其內容");
		System.out.println(indexController.getNewCoin());
	}

}
