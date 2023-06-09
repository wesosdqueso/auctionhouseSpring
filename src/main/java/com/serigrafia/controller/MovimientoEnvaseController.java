package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.MovimientoEnvaseDTO;
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
@RequestMapping("movimientoEnvase")
@CrossOrigin
public class MovimientoEnvaseController {
	
	@Autowired
	MovimientoEnvaseService movimientoEnvaseService;
	
	@Autowired
	EnvaseService envaseService;
	
	@Autowired
	AlmacenService almacenService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<MovimientoEnvase> list = movimientoEnvaseService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!movimientoEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		MovimientoEnvase movimientoEnvase = movimientoEnvaseService.getOneById(id).get();
		return new ResponseEntity(movimientoEnvase, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody MovimientoEnvaseDTO movimientoEnvaseDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		MovimientoEnvase movimientoEnvase = new MovimientoEnvase();
		movimientoEnvase.setCantidad(movimientoEnvaseDTO.getCantidad());
		movimientoEnvase.setFecha(movimientoEnvaseDTO.getFecha());
		movimientoEnvase.setNumGuia(movimientoEnvaseDTO.getNumGuia());
		Optional<Almacen> almacenOrigen = almacenService.getOneById(movimientoEnvaseDTO.getAlmacenOrigen().getId());
		if (almacenOrigen.isPresent()) {
			movimientoEnvase.setAlmacen1(almacenOrigen.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoEnvase: almacenOrigen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Almacen> almacenDestino = almacenService.getOneById(movimientoEnvaseDTO.getAlmacenDestino().getId());
		if (almacenDestino.isPresent()) {
			movimientoEnvase.setAlmacen2(almacenDestino.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoEnvase: almacenDestino no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Envase> envase = envaseService.getOneById(movimientoEnvaseDTO.getEnvase().getId());
		if (envase.isPresent()) {
			movimientoEnvase.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoEnvase: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		movimientoEnvaseService.save(movimientoEnvase);
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody MovimientoEnvaseDTO movimientoEnvaseDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!movimientoEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		MovimientoEnvase movimientoEnvase = movimientoEnvaseService.getOneById(id).get();
		movimientoEnvase.setCantidad(movimientoEnvaseDTO.getCantidad());
		movimientoEnvase.setFecha(movimientoEnvaseDTO.getFecha());
		movimientoEnvase.setNumGuia(movimientoEnvaseDTO.getNumGuia());
		Optional<Almacen> almacenOrigen = almacenService.getOneById(movimientoEnvaseDTO.getAlmacenOrigen().getId());
		if (almacenOrigen.isPresent()) {
			movimientoEnvase.setAlmacen1(almacenOrigen.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoEnvase: almacenOrigen no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Almacen> almacenDestino = almacenService.getOneById(movimientoEnvaseDTO.getAlmacenDestino().getId());
		if (almacenDestino.isPresent()) {
			movimientoEnvase.setAlmacen2(almacenDestino.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoEnvase: almacenDestino no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Envase> envase = envaseService.getOneById(movimientoEnvaseDTO.getEnvase().getId());
		if (envase.isPresent()) {
			movimientoEnvase.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update MovimientoEnvase: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		movimientoEnvaseService.save(movimientoEnvase);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!movimientoEnvaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			movimientoEnvaseService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
