package my.company.coin.service;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;

@Component
public class BaseService {
	ObjectMapper objectMapper = new ObjectMapper();
	@Setter(onMethod_ = { @Autowired })
	private RestTemplate restTemplate;

	public BaseService() {
	}

	@Autowired
	public BaseService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public JSONObject get(URI url, Object param, HttpHeaders headers) {
		HttpEntity<String> httpEntity = new HttpEntity<String>(param == null ? null : param.toString(), headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return new JSONObject(response.getBody());
		} else {
			return null;
		}
	}

	public JSONObject get(String url) {
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return get(uri, null, null);
	}
}
