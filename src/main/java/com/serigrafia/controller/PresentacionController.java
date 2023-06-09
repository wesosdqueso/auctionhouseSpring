package com.serigrafia.controller;

import com.serigrafia.dto.Message;
import com.serigrafia.dto.PresentacionDTO;
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
@RequestMapping("presentacion")
@CrossOrigin
public class PresentacionController {
	
	@Autowired
	PresentacionService presentacionService;
	
	@Autowired
	MarcaService marcaService;
	
	@Autowired
	EnvaseService envaseService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<?>> list() {
		List<Presentacion> list = presentacionService.findAll();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {
		if (!presentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		Presentacion presentacion = presentacionService.getOneById(id).get();
		return new ResponseEntity(presentacion, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody PresentacionDTO presentacionDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (presentacionService.existsByDescripcion(presentacionDTO.getDescripcion())) {
			return new ResponseEntity(new Message("Descripcion duplicated"), HttpStatus.BAD_REQUEST);
		}
		Presentacion presentacion = new Presentacion();
		presentacion.setDescripcion(presentacionDTO.getDescripcion());
		Optional<Envase> envase = envaseService.getOneById(presentacionDTO.getEnvase().getId());
		if (envase.isPresent()) {
			presentacion.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update Presentacion: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Marca> marca = marcaService.getOneById(presentacionDTO.getMarca().getId());
		if (marca.isPresent()) {
			presentacion.setMarca(marca.get());
		}
		else {
			return new ResponseEntity(new Message("Update Presentacion: marca no existe"), HttpStatus.BAD_REQUEST);
		}
		presentacionService.save(presentacion);
		if (presentacionService.existsByDescripcion(presentacionDTO.getDescripcion())) {
			return new ResponseEntity(presentacionService.getOneByDescripcion(presentacionDTO.getDescripcion()), HttpStatus.CREATED);
		}
		return new ResponseEntity(new Message("Created"), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PresentacionDTO presentacionDTO, BindingResult bindingResult, @PathVariable("id") int id) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Message("Invalid data"), HttpStatus.BAD_REQUEST);
		}
		if (!presentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		if (presentacionService.existsByDescripcion(presentacionDTO.getDescripcion()) && presentacionService.getOneByDescripcion(presentacionDTO.getDescripcion()).get().getId() != id) {
			return new ResponseEntity(new Message("Descripcion duplicated"), HttpStatus.BAD_REQUEST);
		}
		Presentacion presentacion = presentacionService.getOneById(id).get();
		presentacion.setDescripcion(presentacionDTO.getDescripcion());
		Optional<Envase> envase = envaseService.getOneById(presentacionDTO.getEnvase().getId());
		if (envase.isPresent()) {
			presentacion.setEnvase(envase.get());
		}
		else {
			return new ResponseEntity(new Message("Update Presentacion: envase no existe"), HttpStatus.BAD_REQUEST);
		}
		Optional<Marca> marca = marcaService.getOneById(presentacionDTO.getMarca().getId());
		if (marca.isPresent()) {
			presentacion.setMarca(marca.get());
		}
		else {
			return new ResponseEntity(new Message("Update Presentacion: marca no existe"), HttpStatus.BAD_REQUEST);
		}
		presentacionService.save(presentacion);
		return new ResponseEntity(new Message("Updated"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!presentacionService.existsById(id)) {
			return new ResponseEntity(new Message("Not found"), HttpStatus.NOT_FOUND);
		}
		try {
			presentacionService.delete(id);
		}
		catch(Exception e) {
			return new ResponseEntity(new Message("Cannot delete or update parent row."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(new Message("Deleted"), HttpStatus.CREATED);
	}
	
}
