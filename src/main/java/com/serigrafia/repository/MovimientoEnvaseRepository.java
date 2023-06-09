package com.serigrafia.repository;

import com.serigrafia.entity.MovimientoEnvase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MovimientoEnvaseRepository extends JpaRepository<MovimientoEnvase, Integer> {
	
	
}
