package com.lottstat.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.lottstat.entity.State;

@Path("/states")
public class StateService {

	@GET
	@Path("{id}")
	public Response getStateByPostalCode(@PathParam("id") String id) {
		System.out.println("id: " + id);
		
		State state = State.valueOfAbbreviation(id);
		
		return Response.status(200).entity("getStateByPostalCode is called, id : " + id + ", state: " + state.name())
				.build();
	}
	
	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(@PathParam("year") int year,
			@PathParam("month") int month, @PathParam("day") int day) {

		String date = year + "/" + month + "/" + day;

		return Response.status(200)
				.entity("getUserHistory is called, year/month/day : " + date)
				.build();

	}
	
}
