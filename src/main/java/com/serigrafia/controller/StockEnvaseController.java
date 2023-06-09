package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.StockEnvaseDTO;
import com.serigrafia.entity.*;
import com.serigrafia.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("stockEnvase")
@CrossOrigin
public class StockEnvaseController {
	
	@Autowired
	StockEnvaseService stockEnvaseService;
	
	@Autowired
	EnvaseService envaseService;
	
	@Autowired
	AlmacenService almacenService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<StockEnvase> list = stockEnvaseService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!stockEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockEnvase stockEnvase = stockEnvaseService.getOneById(id).get();
		return new ResponseEntity(stockEnvase, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody StockEnvaseDTO stockEnvaseDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		StockEnvase stockEnvase = new StockEnvase();
		stockEnvase.setCantidad(stockEnvaseDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockEnvaseDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockEnvase.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockEnvase: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Envase> envase = envaseService.getOneById(stockEnvaseDTO.getEnvase().getId());
		if (envase.isPresent()) {
			stockEnvase.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockEnvase: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		stockEnvaseService.save(stockEnvase);
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody StockEnvaseDTO stockEnvaseDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!stockEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockEnvase stockEnvase = stockEnvaseService.getOneById(id).get();
		stockEnvase.setCantidad(stockEnvaseDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockEnvaseDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockEnvase.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockEnvase: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Envase> envase = envaseService.getOneById(stockEnvaseDTO.getEnvase().getId());
		if (envase.isPresent()) {
			stockEnvase.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockEnvase: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		stockEnvaseService.save(stockEnvase);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!stockEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			stockEnvaseService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
