package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.EnvaseDTO;
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
@RequestMapping("envase")
@CrossOrigin
public class EnvaseController {
	
	@Autowired
	EnvaseService envaseService;
	
	@Autowired
	ProductorService productorService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<Envase> list = envaseService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!envaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		Envase envase = envaseService.getOneById(id).get();
		return new ResponseEntity(envase, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody EnvaseDTO envaseDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (envaseService.existsByDescripcion(envaseDTO.getDescripcion())) {
			return new ResponseEntity(new Message("Descripcion duplicated"), HttpStatus.BAD_REQUEST);
		}
		Envase envase = new Envase();
		envase.setDescripcion(envaseDTO.getDescripcion());
		Optional<Productor> productor = productorService.getOneById(envaseDTO.getProductor().getId());
		if (productor.isPresent()) {
			envase.setProductor(productor.get());
		}
		else {
			return new ResponseEntity(new Message("Update Envase: productor no existe"), HttpStatus.BAD_REQUEST);
		}
		envaseService.save(envase);
		if (envaseService.existsByDescripcion(envaseDTO.getDescripcion())) {
			return new ResponseEntity(envaseService.getOneByDescripcion(envaseDTO.getDescripcion()), HttpStatus.CREATED);
		}
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody EnvaseDTO envaseDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!envaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		if (envaseService.existsByDescripcion(envaseDTO.getDescripcion()) && envaseService.getOneByDescripcion(envaseDTO.getDescripcion()).get().getId() != id) {
			return new ResponseEntity(new Message("Descripcion duplicated"), HttpStatus.BAD_REQUEST);
		}
		Envase envase = envaseService.getOneById(id).get();
		envase.setDescripcion(envaseDTO.getDescripcion());
		Optional<Productor> productor = productorService.getOneById(envaseDTO.getProductor().getId());
		if (productor.isPresent()) {
			envase.setProductor(productor.get());
		}
		else {
			return new ResponseEntity(new Message("Update Envase: productor no existe"), HttpStatus.BAD_REQUEST);
		}
		envaseService.save(envase);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!envaseService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			envaseService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
