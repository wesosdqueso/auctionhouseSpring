package com.serigrafia.service;

import com.serigrafia.entity.Envase;
import com.serigrafia.repository.EnvaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnvaseService {
	
	@Autowired
	EnvaseRepository envaseRepository;
	
	@Transactional(readOnly = true)
	public List<Envase> findAll() {
		return envaseRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Envase> getOneById(int id) {
		return envaseRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Envase> getOneByDescripcion(String descripcion) {
		return envaseRepository.findByDescripcion(descripcion);
	}
	
	public void save(Envase envase) {
		envaseRepository.save(envase);
	}
	
	public void delete(int id) {
		envaseRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return envaseRepository.existsById(id);
	}
	
	public boolean existsByDescripcion(String descripcion) {
		return envaseRepository.existsByDescripcion(descripcion);
	}
	
}
