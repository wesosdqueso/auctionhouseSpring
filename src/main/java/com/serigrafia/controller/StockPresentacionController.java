package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.StockPresentacionDTO;
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
@RequestMapping("stockPresentacion")
@CrossOrigin
public class StockPresentacionController {
	
	@Autowired
	StockPresentacionService stockPresentacionService;
	
	@Autowired
	AlmacenService almacenService;
	
	@Autowired
	PresentacionService presentacionService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<StockPresentacion> list = stockPresentacionService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!stockPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockPresentacion stockPresentacion = stockPresentacionService.getOneById(id).get();
		return new ResponseEntity(stockPresentacion, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody StockPresentacionDTO stockPresentacionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		StockPresentacion stockPresentacion = new StockPresentacion();
		stockPresentacion.setCantidad(stockPresentacionDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockPresentacionDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockPresentacion.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockPresentacion: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Presentacion> presentacion = presentacionService.getOneById(stockPresentacionDTO.getPresentacion().getId());
		if (presentacion.isPresent()) {
			stockPresentacion.setPresentacion(presentacion.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockPresentacion: presentacion no existe"), HttpStatus.BAD_REQUEST);
		}
		stockPresentacionService.save(stockPresentacion);
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody StockPresentacionDTO stockPresentacionDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!stockPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockPresentacion stockPresentacion = stockPresentacionService.getOneById(id).get();
		stockPresentacion.setCantidad(stockPresentacionDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockPresentacionDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockPresentacion.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockPresentacion: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Presentacion> presentacion = presentacionService.getOneById(stockPresentacionDTO.getPresentacion().getId());
		if (presentacion.isPresent()) {
			stockPresentacion.setPresentacion(presentacion.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockPresentacion: presentacion no existe"), HttpStatus.BAD_REQUEST);
		}
		stockPresentacionService.save(stockPresentacion);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!stockPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			stockPresentacionService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
