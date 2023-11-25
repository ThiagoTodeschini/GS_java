package br.com.fiap.sintomas.service;

import br.com.fiap.sintomas.data.SintomasDao;
import jakarta.ws.rs.core.Response;

public class SintomasService {
	private SintomasDao sintDao = new SintomasDao();
	
	public Response consultarSintoma(String id) {
		return sintDao.consultarSintoma(id);
	}

}
