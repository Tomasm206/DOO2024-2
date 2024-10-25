package co.edu.uco.ucobet.domain;

import java.util.UUID;

import co.edu.uco.crosscutting.helpers.UUIDHelper;

class Domain {

	private UUID id;

	protected Domain(final UUID id) {
		setId(id);
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}

}