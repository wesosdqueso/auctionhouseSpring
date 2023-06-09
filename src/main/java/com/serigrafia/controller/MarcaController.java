package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.MarcaDTO;
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
@RequestMapping("marca")
@CrossOrigin
public class MarcaController {
	
	@Autowired
	MarcaService marcaService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<Marca> list = marcaService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!marcaService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		Marca marca = marcaService.getOneById(id).get();
		return new ResponseEntity(marca, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody MarcaDTO marcaDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (marcaService.existsByNombre(marcaDTO.getNombre())) {
			return new ResponseEntity(new Message("Nombre duplicated"), HttpStatus.BAD_REQUEST);
		}
		Marca marca = new Marca();
		marca.setNombre(marcaDTO.getNombre());
		marcaService.save(marca);
		if (marcaService.existsByNombre(marcaDTO.getNombre())) {
			return new ResponseEntity(marcaService.getOneByNombre(marcaDTO.getNombre()), HttpStatus.CREATED);
		}
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody MarcaDTO marcaDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!marcaService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		if (marcaService.existsByNombre(marcaDTO.getNombre()) && marcaService.getOneByNombre(marcaDTO.getNombre()).get().getId() != id) {
			return new ResponseEntity(new Message("Nombre duplicated"), HttpStatus.BAD_REQUEST);
		}
		Marca marca = marcaService.getOneById(id).get();
		marca.setNombre(marcaDTO.getNombre());
		marcaService.save(marca);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!marcaService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			marcaService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
