package org.kpt.foodie.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kpt.foodie.LocationItem;
import com.kpt.foodie.QueryHandler;
import com.kpt.foodie.RatingResponse;
import com.kpt.foodie.RestHandler;

@Path("/location")
public class LocationResource {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LocationItem> getAllLocations() {
		return QueryHandler.getInstance().getAllLocations();
	}

	@GET
	@Path("/{location}/{count}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RatingResponse> getBusiness(@PathParam("location") String location, @PathParam("count") int count) {
		return RestHandler.getInstance().execute(location, count);
	}

	@GET
	@Path("/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RatingResponse> getAllBusiness(@PathParam("location") String location) {
		return RestHandler.getInstance().execute(location, null);
	}
}
