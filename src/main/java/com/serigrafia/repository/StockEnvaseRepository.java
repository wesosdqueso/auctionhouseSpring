package com.serigrafia.repository;

import com.serigrafia.entity.StockEnvase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockEnvaseRepository extends JpaRepository<StockEnvase, Integer> {
	
	
}
