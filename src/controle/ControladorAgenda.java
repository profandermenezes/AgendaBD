package controle;

import java.util.ArrayList;

import modelo.Contato;
import modelo.dao.ContatoDao;

public class ControladorAgenda {
	
	ContatoDao dao;
	
	public ControladorAgenda() {
		dao = new ContatoDao();
	}
	
	public boolean cadastrarContato(String nome, String telefone, String email) {
		
		Contato contato = new Contato();
		
		contato.setNome(nome);
		contato.setTelefone(telefone);
		contato.setEmail(email);
		
		return dao.adicionarContato(contato);
		
	}
	
	public String obterContatos() { 
		
		ArrayList<Contato> listaContatos = dao.listarContatos();
		
		String contatos = "";
		
		for(Contato c : listaContatos) {
			contatos += c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail() + "\n"; 
		}
		
		return contatos;
	}
	
	public void fecharConexao() {
		dao.encerrarConexao();
	}

}
