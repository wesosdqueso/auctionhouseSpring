package com.serigrafia.service;

import com.serigrafia.entity.MovimientoEnvase;
import com.serigrafia.repository.MovimientoEnvaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovimientoEnvaseService {
	
	@Autowired
	MovimientoEnvaseRepository movimientoEnvaseRepository;
	
	@Transactional(readOnly = true)
	public List<MovimientoEnvase> findAll() {
		return movimientoEnvaseRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<MovimientoEnvase> getOneById(int id) {
		return movimientoEnvaseRepository.findById(id);
	}
	
	public void save(MovimientoEnvase movimientoEnvase) {
		movimientoEnvaseRepository.save(movimientoEnvase);
	}
	
	public void delete(int id) {
		movimientoEnvaseRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return movimientoEnvaseRepository.existsById(id);
	}
	
}
