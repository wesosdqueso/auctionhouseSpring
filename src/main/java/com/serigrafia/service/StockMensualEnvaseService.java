package com.serigrafia.service;

import com.serigrafia.entity.StockMensualEnvase;
import com.serigrafia.repository.StockMensualEnvaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockMensualEnvaseService {
	
	@Autowired
	StockMensualEnvaseRepository stockMensualEnvaseRepository;
	
	@Transactional(readOnly = true)
	public List<StockMensualEnvase> findAll() {
		return stockMensualEnvaseRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<StockMensualEnvase> getOneById(int id) {
		return stockMensualEnvaseRepository.findById(id);
	}
	
	public void save(StockMensualEnvase stockMensualEnvase) {
		stockMensualEnvaseRepository.save(stockMensualEnvase);
	}
	
	public void delete(int id) {
		stockMensualEnvaseRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return stockMensualEnvaseRepository.existsById(id);
	}
	
}
