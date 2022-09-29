package my.company.coin.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.Getter;
import lombok.Setter;
/**
 * {"time":{"updated":"Sep 27, 2022 07:28:00 UTC","updatedISO":"2022-09-27T07:28:00+00:00",
 * "updateduk":"Sep 27, 2022 at 08:28 BST"},
 * "disclaimer":"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
 * "chartName":"Bitcoin",
 * "bpi":{"USD":{"code":"USD","symbol":"&#36;","rate":"20,232.8155","description":"United States Dollar","rate_float":20232.8155},"GBP":{"code":"GBP","symbol":"&pound;","rate":"16,906.3788","description":"British Pound Sterling","rate_float":16906.3788},"EUR":{"code":"EUR","symbol":"&euro;","rate":"19,709.7163","description":"Euro","rate_float":19709.7163}}
 * }
 * @author user
 *
 */
@Entity
@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonType.class)
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    Timestamp time;

    @Column
    String disclaimer;
    
    @Column
    String chartName;
    

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Map<String, Object> bpi = new HashMap<>();

}
