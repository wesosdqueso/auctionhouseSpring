package com.serigrafia.service;

import com.serigrafia.entity.Almacen;
import com.serigrafia.repository.AlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlmacenService {
	
	@Autowired
	AlmacenRepository almacenRepository;
	
	@Transactional(readOnly = true)
	public List<Almacen> findAll() {
		return almacenRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Almacen> getOneById(int id) {
		return almacenRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Almacen> getOneByNombre(String nombre) {
		return almacenRepository.findByNombre(nombre);
	}
	
	public void save(Almacen almacen) {
		almacenRepository.save(almacen);
	}
	
	public void delete(int id) {
		almacenRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return almacenRepository.existsById(id);
	}
	
	public boolean existsByNombre(String nombre) {
		return almacenRepository.existsByNombre(nombre);
	}
	
}
