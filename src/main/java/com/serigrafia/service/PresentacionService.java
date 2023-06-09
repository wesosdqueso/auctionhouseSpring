package com.serigrafia.service;

import com.serigrafia.entity.Presentacion;
import com.serigrafia.repository.PresentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PresentacionService {
	
	@Autowired
	PresentacionRepository presentacionRepository;
	
	@Transactional(readOnly = true)
	public List<Presentacion> findAll() {
		return presentacionRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Presentacion> getOneById(int id) {
		return presentacionRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Presentacion> getOneByDescripcion(String descripcion) {
		return presentacionRepository.findByDescripcion(descripcion);
	}
	
	public void save(Presentacion presentacion) {
		presentacionRepository.save(presentacion);
	}
	
	public void delete(int id) {
		presentacionRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return presentacionRepository.existsById(id);
	}
	
	public boolean existsByDescripcion(String descripcion) {
		return presentacionRepository.existsByDescripcion(descripcion);
	}
	
}
