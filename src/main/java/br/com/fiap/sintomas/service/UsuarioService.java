package br.com.fiap.sintomas.service;

import br.com.fiap.sintomas.data.UsuarioDao;
import br.com.fiap.sintomas.model.Usuario;
import jakarta.ws.rs.core.Response;

public class UsuarioService {
	private UsuarioDao userDao = new UsuarioDao();

	public Response consultar(String cpf) {
		return userDao.consultar(cpf);
	}
	
	public void cadastrar(Usuario user) {
		userDao.inserir(user);
	}

	public Response excluirUsuario(String cpf) {
		return userDao.excluirUsuario(cpf);
	}
}
