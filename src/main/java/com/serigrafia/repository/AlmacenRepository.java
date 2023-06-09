package com.serigrafia.repository;

import com.serigrafia.entity.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
	
	boolean existsByNombre (String nombre);
	
	Optional<Almacen> findByNombre (String nombre);
	
	
}
