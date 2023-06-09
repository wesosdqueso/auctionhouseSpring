package com.serigrafia.service;

import com.serigrafia.entity.Productor;
import com.serigrafia.repository.ProductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductorService {
	
	@Autowired
	ProductorRepository productorRepository;
	
	@Transactional(readOnly = true)
	public List<Productor> findAll() {
		return productorRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Productor> getOneById(int id) {
		return productorRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Productor> getOneByNombre(String nombre) {
		return productorRepository.findByNombre(nombre);
	}
	
	public void save(Productor productor) {
		productorRepository.save(productor);
	}
	
	public void delete(int id) {
		productorRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return productorRepository.existsById(id);
	}
	
	public boolean existsByNombre(String nombre) {
		return productorRepository.existsByNombre(nombre);
	}
	
}
