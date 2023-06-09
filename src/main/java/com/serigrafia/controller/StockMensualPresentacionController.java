package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.StockMensualPresentacionDTO;
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
@RequestMapping("stockMensualPresentacion")
@CrossOrigin
public class StockMensualPresentacionController {
	
	@Autowired
	StockMensualPresentacionService stockMensualPresentacionService;
	
	@Autowired
	AlmacenService almacenService;
	
	@Autowired
	PresentacionService presentacionService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<StockMensualPresentacion> list = stockMensualPresentacionService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!stockMensualPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockMensualPresentacion stockMensualPresentacion = stockMensualPresentacionService.getOneById(id).get();
		return new ResponseEntity(stockMensualPresentacion, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody StockMensualPresentacionDTO stockMensualPresentacionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		StockMensualPresentacion stockMensualPresentacion = new StockMensualPresentacion();
		stockMensualPresentacion.setMes(stockMensualPresentacionDTO.getMes());
		stockMensualPresentacion.setCantidad(stockMensualPresentacionDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockMensualPresentacionDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockMensualPresentacion.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualPresentacion: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Presentacion> presentacion = presentacionService.getOneById(stockMensualPresentacionDTO.getPresentacion().getId());
		if (presentacion.isPresent()) {
			stockMensualPresentacion.setPresentacion(presentacion.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualPresentacion: presentacion no existe"), HttpStatus.BAD_REQUEST);
		}
		stockMensualPresentacionService.save(stockMensualPresentacion);
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody StockMensualPresentacionDTO stockMensualPresentacionDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!stockMensualPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		StockMensualPresentacion stockMensualPresentacion = stockMensualPresentacionService.getOneById(id).get();
		stockMensualPresentacion.setMes(stockMensualPresentacionDTO.getMes());
		stockMensualPresentacion.setCantidad(stockMensualPresentacionDTO.getCantidad());
		Optional<Almacen> almacen = almacenService.getOneById(stockMensualPresentacionDTO.getAlmacen().getId());
		if (almacen.isPresent()) {
			stockMensualPresentacion.setAlmacen(almacen.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualPresentacion: almacen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Presentacion> presentacion = presentacionService.getOneById(stockMensualPresentacionDTO.getPresentacion().getId());
		if (presentacion.isPresent()) {
			stockMensualPresentacion.setPresentacion(presentacion.get());
		}
		else {
			return new ResponseEntity(new Message("Update StockMensualPresentacion: presentacion no existe"), HttpStatus.BAD_REQUEST);
		}
		stockMensualPresentacionService.save(stockMensualPresentacion);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!stockMensualPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			stockMensualPresentacionService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
