package com.serigrafia.repository;

import com.serigrafia.entity.StockMensualPresentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockMensualPresentacionRepository extends JpaRepository<StockMensualPresentacion, Integer> {
	
	
}
