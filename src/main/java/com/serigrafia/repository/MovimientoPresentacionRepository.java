package com.serigrafia.repository;

import com.serigrafia.entity.MovimientoPresentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MovimientoPresentacionRepository extends JpaRepository<MovimientoPresentacion, Integer> {
	
	
}
