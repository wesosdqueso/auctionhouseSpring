package com.serigrafia.service;

import com.serigrafia.entity.StockMensualPresentacion;
import com.serigrafia.repository.StockMensualPresentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockMensualPresentacionService {
	
	@Autowired
	StockMensualPresentacionRepository stockMensualPresentacionRepository;
	
	@Transactional(readOnly = true)
	public List<StockMensualPresentacion> findAll() {
		return stockMensualPresentacionRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<StockMensualPresentacion> getOneById(int id) {
		return stockMensualPresentacionRepository.findById(id);
	}
	
	public void save(StockMensualPresentacion stockMensualPresentacion) {
		stockMensualPresentacionRepository.save(stockMensualPresentacion);
	}
	
	public void delete(int id) {
		stockMensualPresentacionRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return stockMensualPresentacionRepository.existsById(id);
	}
	
}
