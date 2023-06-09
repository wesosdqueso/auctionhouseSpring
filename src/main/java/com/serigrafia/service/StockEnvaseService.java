package com.serigrafia.service;

import com.serigrafia.entity.StockEnvase;
import com.serigrafia.repository.StockEnvaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockEnvaseService {
	
	@Autowired
	StockEnvaseRepository stockEnvaseRepository;
	
	@Transactional(readOnly = true)
	public List<StockEnvase> findAll() {
		return stockEnvaseRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<StockEnvase> getOneById(int id) {
		return stockEnvaseRepository.findById(id);
	}
	
	public void save(StockEnvase stockEnvase) {
		stockEnvaseRepository.save(stockEnvase);
	}
	
	public void delete(int id) {
		stockEnvaseRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return stockEnvaseRepository.existsById(id);
	}
	
}
