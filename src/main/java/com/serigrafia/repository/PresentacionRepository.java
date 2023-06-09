package com.serigrafia.repository;

import com.serigrafia.entity.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PresentacionRepository extends JpaRepository<Presentacion, Integer> {
	
	boolean existsByDescripcion (String descripcion);
	
	Optional<Presentacion> findByDescripcion (String descripcion);
	
	
}
