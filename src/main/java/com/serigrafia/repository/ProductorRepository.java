package com.serigrafia.repository;

import com.serigrafia.entity.Productor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductorRepository extends JpaRepository<Productor, Integer> {
	
	boolean existsByNombre (String nombre);
	
	Optional<Productor> findByNombre (String nombre);
	
	
}
