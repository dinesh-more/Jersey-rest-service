package com.jersey;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

	AlienRepository repo = new AlienRepository();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Alien> getAliens() {
		return repo.getAliens();
	}

	@GET
	@Path("alien/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Alien getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}

	@POST
	@Path("alien")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Alien createAlien(Alien a) {
		repo.create(a);
		return a;
	}

	@PUT
	@Path("update")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Alien updateAlien(Alien a) {
		if (repo.getAlien(a.getId()).getId() == 0) {
			repo.create(a);
			return a;
		} else {
			repo.update(a);
			return a;
		}
	}

	@DELETE
	@Path("alien/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deleteAlien(@PathParam("id") int id) {
		Alien a = repo.getAlien(id);
		if (a.getId() != 0)
			repo.deleteAlien(id);
	}
}
