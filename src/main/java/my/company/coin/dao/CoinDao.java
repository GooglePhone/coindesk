package my.company.coin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.company.coin.entity.Coin;

@Repository
public interface CoinDao extends JpaRepository<Coin, Integer>{
	
}
