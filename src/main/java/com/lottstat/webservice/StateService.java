package com.lottstat.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.lottstat.converter.Converter;
import com.lottstat.dao.StateDataDAO;
import com.lottstat.dao.impl.DefaultStateDataDAO;
import com.lottstat.entity.State;
import com.lottstat.entity.StateEnum;

@Path("/states")
public class StateService {

	@GET
	@Path("{id}")
	@Produces("application/json")
	public State getStateByPostalCode(@PathParam("id") String id) {
		
		StateDataDAO dao = new DefaultStateDataDAO();
		StateEnum stateEnum = StateEnum.valueOfAbbreviation(id);
		
		String html = dao.getStateData("http://www.flalottery.com/remainingPrizes");
		
		Converter converter = Converter.getInstance(stateEnum);
		
		State state = converter.convertState(html);
		return state;
	}
	
}
