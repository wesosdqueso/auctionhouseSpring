package com.serigrafia.repository;

import com.serigrafia.entity.StockMensualEnvase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockMensualEnvaseRepository extends JpaRepository<StockMensualEnvase, Integer> {
	
	
}
