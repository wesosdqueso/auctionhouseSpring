package com.serigrafia.service;

import com.serigrafia.entity.MovimientoPresentacion;
import com.serigrafia.repository.MovimientoPresentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovimientoPresentacionService {
	
	@Autowired
	MovimientoPresentacionRepository movimientoPresentacionRepository;
	
	@Transactional(readOnly = true)
	public List<MovimientoPresentacion> findAll() {
		return movimientoPresentacionRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<MovimientoPresentacion> getOneById(int id) {
		return movimientoPresentacionRepository.findById(id);
	}
	
	public void save(MovimientoPresentacion movimientoPresentacion) {
		movimientoPresentacionRepository.save(movimientoPresentacion);
	}
	
	public void delete(int id) {
		movimientoPresentacionRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return movimientoPresentacionRepository.existsById(id);
	}
	
}
