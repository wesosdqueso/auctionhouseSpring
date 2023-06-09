package com.serigrafia.repository;

import com.serigrafia.entity.StockPresentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockPresentacionRepository extends JpaRepository<StockPresentacion, Integer> {
	
	
}
