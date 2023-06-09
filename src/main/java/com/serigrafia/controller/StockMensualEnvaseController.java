package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.StockMensualEnvaseDTO;
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
@RequestMapping("stockMensualEnvase")
@CrossOrigin
public class StockMensualEnvaseController {
	
	@Autowired
	StockMensualEnvaseService stockMensualEnvaseService;
	
	@Autowired
	EnvaseService envaseService;
	
	@Autowired
	AlmacenService almacenService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<StockMensualEnvase> list = stockMensualEnvaseService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!stockMensualEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockMensualEnvase stockMensualEnvase = stockMensualEnvaseService.getOneById(id).get();
		return new ResponseEntity(stockMensualEnvase, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody StockMensualEnvaseDTO stockMensualEnvaseDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		StockMensualEnvase stockMensualEnvase = new StockMensualEnvase();
		stockMensualEnvase.setMes(stockMensualEnvaseDTO.getMes());
		stockMensualEnvase.setCantidad(stockMensualEnvaseDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockMensualEnvaseDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockMensualEnvase.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualEnvase: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Envase> envase = envaseService.getOneById(stockMensualEnvaseDTO.getEnvase().getId());
		if (envase.isPresent()) {
			stockMensualEnvase.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualEnvase: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		stockMensualEnvaseService.save(stockMensualEnvase);
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody StockMensualEnvaseDTO stockMensualEnvaseDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!stockMensualEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockMensualEnvase stockMensualEnvase = stockMensualEnvaseService.getOneById(id).get();
		stockMensualEnvase.setMes(stockMensualEnvaseDTO.getMes());
		stockMensualEnvase.setCantidad(stockMensualEnvaseDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockMensualEnvaseDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockMensualEnvase.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualEnvase: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Envase> envase = envaseService.getOneById(stockMensualEnvaseDTO.getEnvase().getId());
		if (envase.isPresent()) {
			stockMensualEnvase.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualEnvase: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		stockMensualEnvaseService.save(stockMensualEnvase);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!stockMensualEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			stockMensualEnvaseService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
