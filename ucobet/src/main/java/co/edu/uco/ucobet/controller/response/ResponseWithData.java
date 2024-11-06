package co.edu.uco.ucobet.controller.response;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.helpers.ObjectHelper;

public class ResponseWithData<T> extends Response {
	
	private List<T> data = new ArrayList<>();

	public final List<T> getData() {
		return data;
	}

	public final void setData(List<T> data) {
		this.data = ObjectHelper.getDefault(data, this.data);
	}
	
	
}
