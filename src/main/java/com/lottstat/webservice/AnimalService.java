package com.lottstat.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/animal")
public class AnimalService {
	@GET
	@Path("{name}")
	public Response getAnimal(@PathParam("name") String name){
		return Response.status(503).entity("What for you say you " +name+ " when you got little powderpuff tail like rabbit? RABBIT!")
				.build();	
	}
}
