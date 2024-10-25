package co.edu.uco.ucobet.businesslogic.adapter.entity;

import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.entity.StateEntity;

public class StateEntityAdapter implements Adapter<StateDomain, StateEntity> {

	private static final Adapter<StateDomain, StateEntity> instance = new StateEntityAdapter();

	private StateEntityAdapter() {

	}

	public static Adapter<StateDomain, StateEntity> getStateEntityAdapter() {
		return instance;
	}

	@Override
	public StateDomain adaptSource(StateEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateEntity adaptTarget(StateDomain data) {
		// TODO Auto-generated method stub
		return null;
	}
}
