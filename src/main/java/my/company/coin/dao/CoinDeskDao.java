package my.company.coin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.company.coin.entity.CoinDesk;

@Repository
public interface CoinDeskDao extends JpaRepository<CoinDesk, String>{
	
}
