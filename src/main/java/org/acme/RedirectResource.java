package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/old-path")
public class RedirectResource {

	@Path("/location")
	@GET
	public Response redirectToNewPath1() {
		return Response
				.status(Response.Status.FOUND)
				.location(java.net.URI.create("/new-path"))
				.build();
	}

	@Path("/location2")
	@GET
	public Response redirectToNewPath2(UriInfo uriInfo) {

		final var path = uriInfo.getBaseUriBuilder().path("/new-path").build();

		return Response
				.status(Response.Status.FOUND)
				.location(path)
				.build();
	}

	@Path("/location3")
	@GET
	public Response redirectToNewPath3(UriInfo uriInfo) {

		final var path = URI.create("https://example.org/new-path");

		return Response
				.status(Response.Status.FOUND)
				.location(path)
				.build();
	}

	@Path("/content-location")
	@GET
	public Response redirectToNewPath2() {
		return Response
				.status(Response.Status.FOUND)
				.contentLocation(java.net.URI.create("/new-path"))
				.build();
	}
}
