package com.serigrafia.service;

import com.serigrafia.entity.Marca;
import com.serigrafia.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarcaService {
	
	@Autowired
	MarcaRepository marcaRepository;
	
	@Transactional(readOnly = true)
	public List<Marca> findAll() {
		return marcaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Marca> getOneById(int id) {
		return marcaRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Marca> getOneByNombre(String nombre) {
		return marcaRepository.findByNombre(nombre);
	}
	
	public void save(Marca marca) {
		marcaRepository.save(marca);
	}
	
	public void delete(int id) {
		marcaRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return marcaRepository.existsById(id);
	}
	
	public boolean existsByNombre(String nombre) {
		return marcaRepository.existsByNombre(nombre);
	}
	
}
