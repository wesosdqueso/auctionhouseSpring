package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.AlmacenDTO;
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
@RequestMapping("almacen")
@CrossOrigin
public class AlmacenController {
	
	@Autowired
	AlmacenService almacenService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<Almacen> list = almacenService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!almacenService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		Almacen almacen = almacenService.getOneById(id).get();
		return new ResponseEntity(almacen, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody AlmacenDTO almacenDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (almacenService.existsByNombre(almacenDTO.getNombre())) {
			return new ResponseEntity(new Message("Nombre duplicated"), HttpStatus.BAD_REQUEST);
		}
		Almacen almacen = new Almacen();
		almacen.setNombre(almacenDTO.getNombre());
		almacen.setDireccion(almacenDTO.getDireccion());
		almacenService.save(almacen);
		if (almacenService.existsByNombre(almacenDTO.getNombre())) {
			return new ResponseEntity(almacenService.getOneByNombre(almacenDTO.getNombre()), HttpStatus.CREATED);
		}
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody AlmacenDTO almacenDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!almacenService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		if (almacenService.existsByNombre(almacenDTO.getNombre()) && almacenService.getOneByNombre(almacenDTO.getNombre()).get().getId() != id) {
			return new ResponseEntity(new Message("Nombre duplicated"), HttpStatus.BAD_REQUEST);
		}
		Almacen almacen = almacenService.getOneById(id).get();
		almacen.setNombre(almacenDTO.getNombre());
		almacen.setDireccion(almacenDTO.getDireccion());
		almacenService.save(almacen);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!almacenService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			almacenService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
