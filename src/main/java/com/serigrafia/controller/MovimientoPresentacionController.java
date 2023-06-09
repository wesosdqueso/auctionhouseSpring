package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.MovimientoPresentacionDTO;
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
@RequestMapping("movimientoPresentacion")
@CrossOrigin
public class MovimientoPresentacionController {
	
	@Autowired
	MovimientoPresentacionService movimientoPresentacionService;
	
	@Autowired
	PresentacionService presentacionService;
	
	@Autowired
	AlmacenService almacenService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<MovimientoPresentacion> list = movimientoPresentacionService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!movimientoPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		MovimientoPresentacion movimientoPresentacion = movimientoPresentacionService.getOneById(id).get();
		return new ResponseEntity(movimientoPresentacion, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody MovimientoPresentacionDTO movimientoPresentacionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		MovimientoPresentacion movimientoPresentacion = new MovimientoPresentacion();
		movimientoPresentacion.setCantidad(movimientoPresentacionDTO.getCantidad());
		movimientoPresentacion.setFecha(movimientoPresentacionDTO.getFecha());
		movimientoPresentacion.setNumGuia(movimientoPresentacionDTO.getNumGuia());
		Optional<Almacen> almacenOrigen = almacenService.getOneById(movimientoPresentacionDTO.getAlmacenOrigen().getId());
		if (almacenOrigen.isPresent()) {
			movimientoPresentacion.setAlmacen1(almacenOrigen.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoPresentacion: almacenOrigen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Almacen> almacenDestino = almacenService.getOneById(movimientoPresentacionDTO.getAlmacenDestino().getId());
		if (almacenDestino.isPresent()) {
			movimientoPresentacion.setAlmacen2(almacenDestino.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoPresentacion: almacenDestino no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Presentacion> presentacion = presentacionService.getOneById(movimientoPresentacionDTO.getPresentacion().getId());
		if (presentacion.isPresent()) {
			movimientoPresentacion.setPresentacion(presentacion.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoPresentacion: presentacion no existe"), HttpStatus.BAD_REQUEST);
		}
		movimientoPresentacionService.save(movimientoPresentacion);
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody MovimientoPresentacionDTO movimientoPresentacionDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!movimientoPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		MovimientoPresentacion movimientoPresentacion = movimientoPresentacionService.getOneById(id).get();
		movimientoPresentacion.setCantidad(movimientoPresentacionDTO.getCantidad());
		movimientoPresentacion.setFecha(movimientoPresentacionDTO.getFecha());
		movimientoPresentacion.setNumGuia(movimientoPresentacionDTO.getNumGuia());
		Optional<Almacen> almacenOrigen = almacenService.getOneById(movimientoPresentacionDTO.getAlmacenOrigen().getId());
		if (almacenOrigen.isPresent()) {
			movimientoPresentacion.setAlmacen1(almacenOrigen.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoPresentacion: almacenOrigen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Almacen> almacenDestino = almacenService.getOneById(movimientoPresentacionDTO.getAlmacenDestino().getId());
		if (almacenDestino.isPresent()) {
			movimientoPresentacion.setAlmacen2(almacenDestino.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoPresentacion: almacenDestino no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Presentacion> presentacion = presentacionService.getOneById(movimientoPresentacionDTO.getPresentacion().getId());
		if (presentacion.isPresent()) {
			movimientoPresentacion.setPresentacion(presentacion.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoPresentacion: presentacion no existe"), HttpStatus.BAD_REQUEST);
		}
		movimientoPresentacionService.save(movimientoPresentacion);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!movimientoPresentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			movimientoPresentacionService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
