package br.com.drogaria.factory;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	// Dados do banco
	private static final String USUARIO = "root";
	private static final String SENHA = "q1w2e3r4";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria";

	// Metodo para realizar e retornar a conexao

	public static java.sql.Connection conectar() throws SQLException {

		// Registro de Driver
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		java.sql.Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

	// Método main para testar conexão
	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			java.sql.Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conexão realizada");
		} catch (SQLException e) {
			System.out.println("Não foi possivel realizar conexão!" + e);
		}
	}
}
