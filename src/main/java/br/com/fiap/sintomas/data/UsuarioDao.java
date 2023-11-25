package br.com.fiap.sintomas.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fiap.sintomas.model.Usuario;
import jakarta.ws.rs.core.Response;

public class UsuarioDao {
	
	
	public void inserir(Usuario usuario) {
		String comandoSql = "INSERT INTO pacientes (nome_paciente, data_nascimento, genero, cpf, contato, senha) VALUES(?,?,?,?,?,?)";

        try (Connection connection = ConnectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(comandoSql)) {
	            preparedStatement.setString(1, usuario.getNome_paciente());
	            java.util.Date utilDate = usuario.getData_nascimento();
	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	            preparedStatement.setDate(2, sqlDate);
	            preparedStatement.setString(3, usuario.getGenero());
	            preparedStatement.setString(4, usuario.getCpf());
	            preparedStatement.setString(5, usuario.getContato());
	            preparedStatement.setString(6, usuario.getSenha());
	            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário", e);
        }
		
    }
	
	
	public Response consultar(String cpf) {
		try (Connection conn = ConnectionFactory.createConnection()) {
			String sql = "SELECT * FROM pacientes WHERE cpf = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cpf);

			System.out.println(sql);
			
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome_paciente(resultSet.getString("nome_paciente"));
				usuario.setData_nascimento(resultSet.getDate("data_nascimento"));
				usuario.setGenero(resultSet.getString("genero"));
				usuario.setCpf(resultSet.getString("cpf"));
				usuario.setContato(resultSet.getString("contato"));
				usuario.setSenha(resultSet.getString("senha"));

				return Response.status(Response.Status.OK).entity(usuario).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao acessar o banco de dados.")
					.build();
		}
	}

    public Response excluirUsuario(String cpf) {
        try (Connection conn = ConnectionFactory.createConnection()) {
            String sql = "DELETE FROM pacientes WHERE cpf = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                return Response.status(Response.Status.OK).entity("Usuário excluído com sucesso").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao acessar o banco de dados.")
                    .build();
        }
    }

}
