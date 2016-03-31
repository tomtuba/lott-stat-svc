package com.lottstat.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.lottstat.util.StateListUtil;

@Path("/state-list")
public class StateListService {
	
	@GET
	public Response getStateList() {
		return Response.status(200).entity(StateListUtil.getLotteryStatesJSON().toString())
		.build();
	}
}
