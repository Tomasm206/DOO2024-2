package co.edu.uco.ucobet.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uco.ucobet.dto.CityDTO;

@RestController
@RequestMapping("/api/v1/cities/")
public final class CityController {
	
	@GetMapping("/dummy")	
	public CityDTO getDummy() {
		return CityDTO.create();
	}
	
	@PostMapping
	public CityDTO create(@RequestBody CityDTO city) {
		return city;
	}
	
	@PutMapping
	public CityDTO update(@PathVariable String id, @RequestBody CityDTO city) {
		city.setId(id);
		return city;
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable String id) {
		return id;
	}
	
	@GetMapping
	public List<CityDTO> retrive() {
		var list = new ArrayList<CityDTO>();
		
		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());
		return list;
	}
	
	@GetMapping("/{id}")
	public CityDTO retriveById(@PathVariable String id) {
		return getDummy().setId(id);
	}
	
}
