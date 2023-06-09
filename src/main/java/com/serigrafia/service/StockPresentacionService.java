package com.serigrafia.service;

import com.serigrafia.entity.StockPresentacion;
import com.serigrafia.repository.StockPresentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockPresentacionService {
	
	@Autowired
	StockPresentacionRepository stockPresentacionRepository;
	
	@Transactional(readOnly = true)
	public List<StockPresentacion> findAll() {
		return stockPresentacionRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<StockPresentacion> getOneById(int id) {
		return stockPresentacionRepository.findById(id);
	}
	
	public void save(StockPresentacion stockPresentacion) {
		stockPresentacionRepository.save(stockPresentacion);
	}
	
	public void delete(int id) {
		stockPresentacionRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return stockPresentacionRepository.existsById(id);
	}
	
}
