package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Padrão Factory (padrão de criação)
 */

public class FabricaConexao {
	
	public static Connection getConexao() {
		
		// Tentar executar os comandos
		try {
			
			// Registrar o driver do SQL Server
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			// Conectando ao banco
			Connection conexao = DriverManager.getConnection("jdbc:sqlserver://172.17.0.3:1433;"
					+ "databaseName=agenda;user=sa;password=my_rO0t_p@ssw0rd");
			
			return conexao;
			
		// Captura a exceção	
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado.");
			e.printStackTrace();
			return null;
			
		} catch (SQLException e) {
			System.out.println("Impossível conectar ao banco.");
			e.printStackTrace();
			return null;
		}
		
	}

}
