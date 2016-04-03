package com.lottstat.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.lottstat.entity.StateEnum;

@Path("/states")
public class StateService {

	@GET
	@Path("{id}")
	public Response getStateByPostalCode(@PathParam("id") String id) {
		
		StateEnum state = StateEnum.valueOfAbbreviation(id);
		
		return Response.status(200).entity("getStateByPostalCode is called, id : " + id + ", state: " + state.toString())
				.build();
	}
	
}
