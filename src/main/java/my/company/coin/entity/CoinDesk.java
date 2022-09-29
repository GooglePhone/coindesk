package my.company.coin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class CoinDesk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String time;

    @Column
    String disclaimer;
    
    @Column
    String chartName;
    
    @Column(columnDefinition="TEXT", length = 2048)
    String bpi;

}
