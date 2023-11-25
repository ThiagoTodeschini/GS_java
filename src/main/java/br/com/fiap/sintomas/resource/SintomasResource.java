package br.com.fiap.sintomas.resource;

import br.com.fiap.sintomas.service.SintomasService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("sintomas")
public class SintomasResource {

	private SintomasService sintServ = new SintomasService();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEspecialistaBySintoma(@PathParam("id") String id) {
		return sintServ.consultarSintoma(id);
	}

}