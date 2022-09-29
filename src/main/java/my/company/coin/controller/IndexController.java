package my.company.coin.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import my.company.coin.entity.Coin;
import my.company.coin.service.CoinService;

@RestController
public class IndexController extends BaseController {

	@Setter(onMethod_ = { @Autowired })
	CoinService coinService;

	@PostMapping(value = "updateNewCoin")
	public ResponseEntity<?> updateNewCoin(Coin coin){
		return responseSuccess(coinService.updateCoin(coin));
	}
	
	@PutMapping(value = "insertNewCoin")
	public ResponseEntity<?> insertNewCoin(JSONObject coin){
		return responseSuccess(coinService.updateCoin(coin));
	}
	
	@GetMapping(value = "initData")
	public ResponseEntity<?> initData(){
		return responseSuccess(coinService.initCoinData());
	}
	
	@GetMapping(value = "getCoinDesk")
	public ResponseEntity<?> getCoinDesk(){
		return responseSuccess(coinService.getCoinDeskInfo());
	}
	
	@GetMapping(value = "getNewCoin")
	public ResponseEntity<?> getNewCoin(){
		return responseSuccess(coinService.getNewCoin());
	}
	
	@DeleteMapping(value = "deleteNewCoin")
	public ResponseEntity<?> deleteNewCoin(Integer id){
		return responseSuccess(coinService.deleteById(id));
	}
}
