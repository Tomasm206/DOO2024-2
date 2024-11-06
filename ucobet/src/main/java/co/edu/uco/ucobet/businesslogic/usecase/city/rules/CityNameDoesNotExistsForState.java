package co.edu.uco.ucobet.businesslogic.usecase.city.rules;

import co.edu.uco.ucobet.businesslogic.usecase.RuleWithFactory;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;

public interface CityNameDoesNotExistsForState extends RuleWithFactory<CityDomain>{

	void execute(CityDomain city, DAOFactory daoFactory);
	
}
