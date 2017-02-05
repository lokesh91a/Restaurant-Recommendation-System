package org.kpt.foodie.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.kpt.foodie.RestHandler;

@Path("/predict")
public class ReviewResource {

	@GET
	@Path("/{data}")
	public String predict(@PathParam("data") String data) {
		return RestHandler.getInstance().getPrediction(data);
	}
}
