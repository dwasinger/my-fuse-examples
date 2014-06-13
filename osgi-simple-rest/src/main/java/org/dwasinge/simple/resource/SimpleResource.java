package org.dwasinge.simple.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.dwasinge.simple.model.SimpleResponse;

@Path("/simple")
public class SimpleResource {

	@Path("/echo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SimpleResponse getResponse() {
		return null;
	}

}
