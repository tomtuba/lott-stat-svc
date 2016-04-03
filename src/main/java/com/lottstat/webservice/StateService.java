package com.lottstat.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;
import com.lottstat.util.MockState;

@Path("/states")
public class StateService {

	@GET
	@Path("{id}")
	@Produces("application/json")
	public State getStateByPostalCode(@PathParam("id") String id) {
		
		StateEnum state = StateEnum.valueOfAbbreviation(id);
		
		return MockState.getMockState();
	}
	
}
