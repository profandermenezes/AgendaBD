package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Contato;

/*
 * Classe responsável por centralizar toda a interação do sistema com o banco. 
 * É comum termos uma classe DAO para cada tabela do banco de dados (ex.: tabela
 * contato e classe ContatoDao, tabela funcionarios e classe FuncionariosDao). 
 */
public class ContatoDao {
	
	private Connection conexao;
	
	public ContatoDao() {
		conexao = FabricaConexao.getConexao();
	}
	
	public void encerrarConexao() {
		try {
			
			conexao.close();
			
		} catch (SQLException e) {
			System.out.println("Problemas ao encerrar conexão.");
			e.printStackTrace();
		}
	}
	
	/*
	 * CRUD -> Create, Read, Update, Delete
	 */
	
	// Create - Insere um novo contato no banco
	public boolean adicionarContato(Contato c) {
		
		/*
		 * String com o comando SQL que será executado. Os "?" serão substituídos
		 * por valores válidos mais a frente.
		 */
		String sql = "INSERT INTO contato (nome, telefone, email) VALUES (?, ?, ?)";
		
		/*
		 * Objeto que possibilita a execução dos comandos SQL. Como quem "conhece"
		 * nosso banco de dados é a conexão, precisaremos da instância do 
		 * PreparedStatement fornecida por ele. 
		 */
		PreparedStatement stmt;
		
		try {
			
			stmt = conexao.prepareStatement(sql);
			
			/*
			 * Subistituição dos "?" pelos dados do contato. Cada interrogação possui
			 * um índice associado.
			 */
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getTelefone());
			stmt.setString(3, c.getEmail());
			
			//Executando o comando SQL no banco de dados
			stmt.execute();
			
			//Encerrando o "executor"
			stmt.close();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Problemas com a execução do comando.");
			e.printStackTrace();
			return false;
		}
		
	}
	
	// Delete - Remove um contato com base no ID
	public boolean removeContato(int id) {
		
		String sql = "DELETE FROM contato WHERE id = ?";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.execute();
			
			stmt.close();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Problemas com a execução do comando.");
			e.printStackTrace();
			return false;
		}
	}
	
	// Update - Atualiza um contato já existente
	public boolean atualizaContato(Contato c) {
		
		String sql = "UPDATE contato SET nome=?, email=?, telefone=? WHERE id=?";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getEmail());
			stmt.setString(3, c.getTelefone());
			stmt.setInt(4, c.getId());
			
			stmt.execute();
			stmt.close();
			
			return true;			
			
		} catch (SQLException e) {
			System.out.println("Problemas com a execução do comando.");
			e.printStackTrace();
			return false;
		}
	}
	
	// Read - Retorna todos os contatos cadastrados
	public ArrayList<Contato> listarContatos() {
		
		String sql = "SELECT * FROM contato";
		ArrayList<Contato> contatos = new ArrayList<Contato>();
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			/*
			 * Nesse caso onde esperamos um retorno do banco, ao invés de utilizarmos o método
			 * execute() utilizaremos o método executeQuery(), que retorna um ResultSet (conjunto
			 * de resultados). Portanto, devemos atribuir o retorno da chamada ao método a uma
			 * referência do tipo ResultSet, neste caso a "rs".
			 */
			ResultSet rs = stmt.executeQuery();
			
			/*
			 * Executaremos o laço enquanto nosso ResultSet possuir um próximo elemento, ou seja,
			 * enquanto existirem contatos para serem listados. Observe que a lógica que estamos
			 * usando aqui não faz sentido no contexto de um sistema web, pois essa saída acontecerá 
			 * através do console. Talvez usar listas seja uma boa solução (procurar por List e
			 * ArrayList).
			 */
			while(rs.next()) {
				
				Contato c = new Contato();
				
				/*
				 * Utilizamos os métodos get do ResultSet passando como argumento o nome da coluna
				 * da tabela que queremos recuperar.
				 */
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				
				contatos.add(c);
			}
			
			//Também precisamos fechar o ResultSet
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("Problemas com a execução do comando.");
			e.printStackTrace();
		}
		
		return contatos;
	}
	
	// Read - Retorna um contato com base no id
	public Contato buscarContato(int id) {
		
		Contato c = new Contato();
		
		String sql = "SELECT * FROM contato WHERE id = ?";
		
		try {
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				//Setando os valores dos atributos de "c" com base no retorno da busca
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
			}
			
			rs.close();
			stmt.close();
			
			return c;
			
		} catch (SQLException e) {
			System.out.println("Problemas com a execução do comando.");
			e.printStackTrace();
			return null;
		}
		
	}

}











