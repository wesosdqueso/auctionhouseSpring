package com.serigrafia.repository;

import com.serigrafia.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	
	boolean existsByNombre (String nombre);
	
	Optional<Marca> findByNombre (String nombre);
	
	
}
