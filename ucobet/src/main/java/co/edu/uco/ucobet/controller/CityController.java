package co.edu.uco.ucobet.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.crosscutting.exceptions.UcoApplicationException;
import co.edu.uco.ucobet.businesslogic.facade.city.impl.RegisterNewCityFacadeImpl;
import co.edu.uco.ucobet.controller.response.GenerateResponse;
import co.edu.uco.ucobet.controller.response.concrete.CityResponse;
import co.edu.uco.ucobet.controller.response.concrete.GenericResponse;
import co.edu.uco.ucobet.crosscutting.exceptions.UcoBetException;
import co.edu.uco.ucobet.dto.CityDTO;

@RestController
@RequestMapping("/api/v1/cities/")
public final class CityController {

	@GetMapping("/dummy")
	public CityDTO getDummy() {
		return CityDTO.create();
	}

	@PostMapping
	public ResponseEntity<GenericResponse> create(@RequestBody CityDTO city) {
		var message = new ArrayList<String>();
		
		try {
			var registerNewCityFacade = new RegisterNewCityFacadeImpl();
			registerNewCityFacade.execute(city);
			
			message.add("Se ha agragado la ciudad correctamente");
			
			return GenerateResponse.generateSuccessResponse(message);
		} catch (final UcoBetException exception) {
			message.add(exception.getUserMessage());
			exception.printStackTrace();
		} catch (final UcoApplicationException exception) {
			message.add(exception.getUserMessage());
			exception.printStackTrace();
		} catch (final Exception exception) {
			message.add(
					"Se ha presentado un problema inesperado tratando de llevar a cabo el registro de la ciudad de forma satisfactoria...");
			exception.printStackTrace();
			
			return GenerateResponse.generateFailedSuccessResponse(message);
		}
		
		message.add("La ciudad se registró de manera satisfactoria");

		return GenerateResponse.generateSuccessResponse(message);
	}

	@PutMapping
	public ResponseEntity<GenericResponse> update(@PathVariable String id, @RequestBody CityDTO city) {
		var message = new ArrayList<String>();

		message.add("La ciudad se actualizó de manera satisfactoria");

		return GenerateResponse.generateSuccessResponse(message);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GenericResponse> delete(@PathVariable String id) {
		var message = new ArrayList<String>();

		message.add("La ciudad se eliminó de manera satisfactoria");

		return GenerateResponse.generateSuccessResponse(message);
	}

	@GetMapping
	public ResponseEntity<CityResponse> retrive() {
		CityResponse responseWithData = new CityResponse();
		var message = new ArrayList<String>();

		message.add("Las ciudades se consultaron de manera satisfactoria");

		var list = new ArrayList<CityDTO>();

		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());
		
		responseWithData.setData(list);
		responseWithData.setData(list);
		
		return ((new GenerateResponse<CityResponse>()).generateSuccessResponseWithData(responseWithData));
	}

	@GetMapping("/{id}")
	public CityDTO retriveById(@PathVariable String id) {
		return getDummy().setId(id);
	}

}
