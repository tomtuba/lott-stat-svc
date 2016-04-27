package com.lottstat.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.lottstat.cache.StateCache;
import com.lottstat.cache.impl.DefaultStateCache;
import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;

@Path("/states")
public class StateService {
	private StateCache stateCache = DefaultStateCache.getInstance();

	@GET
	@Path("{id}")
	@Produces("application/json")
	public State getStateByPostalCode(@PathParam("id") String id) {
		StateEnum stateEnum = StateEnum.valueOfAbbreviation(id);
		
		return stateCache.getState(stateEnum);
	}
	
}
