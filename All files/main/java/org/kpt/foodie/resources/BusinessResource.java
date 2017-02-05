package org.kpt.foodie.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kpt.foodie.QueryHandler;
import com.kpt.foodie.ReviewItem;

@Path("/business")
public class BusinessResource {
	@GET
	@Path("/{busid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReviewItem> getAllReviews(@PathParam("busid") String busId) {
		System.out.println("IN BUS");
		return QueryHandler.getInstance().getReviews(busId, null, null);
	}
	
	@GET
	@Path("/{busid}/{count}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReviewItem> getAllReviews(@PathParam("busid") String busId, @PathParam("count") int count) {
		System.out.println("IN BUS SENTIMENT COUNT");
		return QueryHandler.getInstance().getReviews(busId, null, count);
	}
	

}
