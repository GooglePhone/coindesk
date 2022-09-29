package my.company.coin.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<?> responseSuccess(Object data) {
        return this.responseData(HttpStatus.OK.value(), data, HttpStatus.OK.getReasonPhrase());
    }
    public ResponseEntity<?> responseData(int HttpStatusCde, Object data, String status) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(HttpStatusCde)
                .headers(responseHeaders)
                .body(data);
    }
}
