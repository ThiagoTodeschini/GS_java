package br.com.fiap.sintomas.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	final static String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    final static String USER = "rm550165";
    final static String PASS = "060605";

    public static Connection createConnection() throws SQLException{
    	try {
            Class.forName("oracle.jdbc.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to Oracle database.");
        }
    }
}
