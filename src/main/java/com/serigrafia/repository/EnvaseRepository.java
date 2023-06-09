package com.serigrafia.repository;

import com.serigrafia.entity.Envase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnvaseRepository extends JpaRepository<Envase, Integer> {
	
	boolean existsByDescripcion (String descripcion);
	
	Optional<Envase> findByDescripcion (String descripcion);
	
	
}
