package co.edu.uco.ucobet.businesslogic.facade.city.impl;

import co.edu.uco.ucobet.businesslogic.adapter.dto.CityDTOAdapter;
import co.edu.uco.ucobet.businesslogic.facade.city.RegisterNewCityFacade;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.RegisterNewCityImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcobetException;
import co.edu.uco.ucobet.crosscutting.exceptions.UcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.data.dao.enums.DAOSource;
import co.edu.uco.ucobet.dto.CityDTO;

public class RegisterNewCityFacadeImpl implements RegisterNewCityFacade{
	
	@Override
	public void execute(CityDTO data) {
//		abrir una conexion lo mas tarde posible y cerrarla lo antes posible 
		var factory = DAOFactory.getFactory(DAOSource.SQLSERVER);

		try {
//			transaccion good
			factory.initTransaction();
			
//			Lo que entra a la capa de negocio son los DTOS
			var registerNewCityUseCase = new RegisterNewCityImpl(factory);
			var cityDomain = CityDTOAdapter.getCityDTOAdapter().adaptSource(data);
			
			
			factory.commitTransaction();
		} catch (final UcoBetException exception) {
			factory.rollbackTransaction(); //Falla la transaccion
			throw exception;
		}  catch (final Exception exception) { 
			factory.rollbackTransaction();
			var usserMessage = "Se ha presentado un problema tratando de registrar la informacion de una nueva ciudad";
			var technicalMessage = "Se ha presentado un problema inesperado registrando la informacion de la nueva ciudad. Por favor revise el log para tener más detalles...";
			
			throw BusinessLogicUcobetException.crear(usserMessage, technicalMessage);
		}finally {
			factory.closeConnection();
		}
	}
}
