package my.company.coin.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import my.company.coin.entity.Coin;
import my.company.coin.utils.TimeUtils;

@Data
public class NewCoinApi {
	Integer id;
	String name;
	String time;
	List<CurrencyInfo> currencyList;

	public NewCoinApi(Coin coin) {
		this.id = coin.getId();
		this.time = TimeUtils.format(coin.getTime());
		this.name = coin.getChartName();
		this.currencyList = coin.getBpi().entrySet().stream().map(e -> {
			CurrencyInfo info = new CurrencyInfo();
			@SuppressWarnings("unchecked")
			Map<String, Object> data = (Map<String, Object>) e.getValue();
			info.setCurrency(e.getKey());
			info.setChineseCode((String) data.get("chineseCode"));
			info.setRate((String) data.get("rate"));
			return info;
		}).collect(Collectors.toList());
	}
}

@Data
class CurrencyInfo {
	String currency;
	String rate;
	String chineseCode;
}