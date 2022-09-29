package my.company.coin.service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import my.company.coin.dao.CoinDao;
import my.company.coin.dto.NewCoinApi;
import my.company.coin.entity.Coin;
import my.company.coin.enums.CurrencyCode;

@Service
public class CoinService extends BaseService {
	@Value("${coindesk.url}")
	String url;
	private CoinDao coinDao;

	public CoinService(CoinDao coinDao) {
		this.coinDao = coinDao;
	}

	public JSONObject getCoinDeskInfo() {
		return this.get(url);
	}

	public JSONObject initCoinData() {
		JSONObject result = getCoinDeskInfo();
		System.out.println(result.toString());
		saveData(result);
		return result;
	}

	public List<NewCoinApi> getNewCoin() {
		List<Coin> list = coinDao.findAll();
		return list.stream().map(d -> {
			return new NewCoinApi(d);
		}).collect(Collectors.toList());
	}

	public Coin saveData(JSONObject data) {
		return coinDao.save(parseJSONtoCoin(data));
	}

	public String deleteById(Integer id) {
		coinDao.deleteById(id);
		return "success";
	}

	public Coin updateCoin(Coin coin) {
		return coinDao.save(coin);
	}

	public Coin updateCoin(JSONObject coin) {
		return coinDao.save(parseJSONtoCoin(coin));
	}

	public List<Coin> findAll() {
		return coinDao.findAll();
	}

	@SuppressWarnings("unchecked")
	public Coin parseJSONtoCoin(JSONObject data) {
		Coin coin = new Coin();
		String isoStr = data.getJSONObject("time").getString("updatedISO");
		ZonedDateTime date = ZonedDateTime.parse(isoStr);
		coin.setTime(Timestamp.valueOf(date.toLocalDateTime()));
		coin.setChartName(data.getString("chartName"));
		coin.setDisclaimer(data.getString("disclaimer"));
		JSONObject bpi = data.getJSONObject("bpi");
		bpi.keys().forEachRemaining(key -> {
			JSONObject obj = bpi.getJSONObject(key);
			obj.put("chineseCode", CurrencyCode.currencyOfCode(key).getChinese());
		});
		Map<String, Object> map = null;
		try {
			map = objectMapper.readValue(bpi.toString(), Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		coin.setBpi(map);
		return coin;
	}
}
