package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.ProductorDTO;
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
@RequestMapping("productor")
@CrossOrigin
public class ProductorController {
	
	@Autowired
	ProductorService productorService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<Productor> list = productorService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!productorService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		Productor productor = productorService.getOneById(id).get();
		return new ResponseEntity(productor, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody ProductorDTO productorDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (productorService.existsByNombre(productorDTO.getNombre())) {
			return new ResponseEntity(new Message("Nombre duplicated"), HttpStatus.BAD_REQUEST);
		}
		Productor productor = new Productor();
		productor.setNombre(productorDTO.getNombre());
		productorService.save(productor);
		if (productorService.existsByNombre(productorDTO.getNombre())) {
			return new ResponseEntity(productorService.getOneByNombre(productorDTO.getNombre()), HttpStatus.CREATED);
		}
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ProductorDTO productorDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!productorService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		if (productorService.existsByNombre(productorDTO.getNombre()) && productorService.getOneByNombre(productorDTO.getNombre()).get().getId() != id) {
			return new ResponseEntity(new Message("Nombre duplicated"), HttpStatus.BAD_REQUEST);
		}
		Productor productor = productorService.getOneById(id).get();
		productor.setNombre(productorDTO.getNombre());
		productorService.save(productor);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!productorService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			productorService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
