package co.edu.uco.ucobet.businesslogic.facade.city.impl;

import java.util.List;

import co.edu.uco.ucobet.businesslogic.adapter.dto.CityDTOAdapter;
import co.edu.uco.ucobet.businesslogic.facade.city.FindCityFacade;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.FindCityImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcobetException;
import co.edu.uco.ucobet.crosscutting.exceptions.UcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.data.dao.enums.DAOSource;
import co.edu.uco.ucobet.dto.CityDTO;

public final class FindCityFacadeImpl implements FindCityFacade {
	
	
	@Override
	public List<CityDTO> execute(CityDTO data) {
		var factory = DAOFactory.getFactory(DAOSource.SQLSERVER);

		try {
//			transaccion good
			
			
//			Lo que entra a la capa de negocio son los DTOS
			var findCityUseCase = new FindCityImpl(factory);
			var cityDomain = CityDTOAdapter.getCityDTOAdapter().adaptSource(data);
			
			return CityDTOAdapter.getCityDTOAdapter().adaptTarget(findCityUseCase.execute(cityDomain));
			

		} catch (final UcoBetException exception) {
//			Falla la transaccion
			throw exception;
		}  catch (final Exception exception) { 
			var usserMessage = "Se ha presentado un problema tratando de consultar la informacion de una nueva ciudad";
			var technicalMessage = "Se ha presentado un problema inesperado consultando la informacion de la nueva ciudad. Por favor revise el log para tener más detalles...";
			
			throw BusinessLogicUcobetException.crear(usserMessage, technicalMessage);
		}finally {
			factory.closeConnection();
		}
	}
	
}
