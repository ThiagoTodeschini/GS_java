package br.com.fiap.sintomas.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.sintomas.model.Sintomas;
import jakarta.ws.rs.core.Response;

public class SintomasDao {

	
	public Response consultarSintoma(String id) {
	    try (Connection conn = ConnectionFactory.createConnection()) {
	        if (id != null) {
	            Integer idSintoma = Integer.parseInt(id);

	            String sql = "SELECT s.descricao_sintomas, e.especialidade, e.id_sintoma FROM sintomas s INNER JOIN especialidades e ON s.id_sintoma = e.id_sintoma WHERE s.id_sintoma = ?";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, idSintoma);

	            ResultSet resultSet = stmt.executeQuery();

	            if (resultSet.next()) {
	                Sintomas sintoma = new Sintomas();
	                sintoma.setId_sintoma(resultSet.getInt("id_sintoma"));
	                sintoma.setSintomas(resultSet.getString("descricao_sintomas"));
	                sintoma.setEspecialidade(resultSet.getString("especialidade"));

	                return Response.status(Response.Status.OK).entity(sintoma).build();
	            } else {
	                return Response.status(Response.Status.NOT_FOUND).entity("Sintoma não encontrado").build();
	            }
	        } else {
	            return Response.status(Response.Status.BAD_REQUEST).entity("ID do sintoma é nulo").build();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao acessar o banco de dados.")
	                .build();
	    } catch (NumberFormatException e) {
	        return Response.status(Response.Status.BAD_REQUEST).entity("ID do sintoma não é um número válido").build();
	    }
	}

}
