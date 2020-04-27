package visao;

import java.util.Scanner;

import controle.ControladorAgenda;

public class Principal {

	public static void main(String[] args) {
		
		ControladorAgenda controlador = new ControladorAgenda();
		
		/*
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Digite o nome do contato: ");
		String nome = scanner.nextLine();
		
		System.out.print("Digite o telefone do contato: ");
		String telefone = scanner.nextLine();
		
		System.out.print("Digite o email do contato: ");
		String email = scanner.nextLine();
		
		if(controlador.cadastrarContato(nome, telefone, email)) {
			System.out.println("Contato cadastrado com sucesso!");
		} else {
			System.out.println("Falha ao cadastrar contato.");
		}
		
		System.out.print("Digite o nome do contato: ");
		nome = scanner.nextLine();
		
		System.out.print("Digite o telefone do contato: ");
		telefone = scanner.nextLine();
		
		System.out.print("Digite o email do contato: ");
		email = scanner.nextLine();
		
		if(controlador.cadastrarContato(nome, telefone, email)) {
			System.out.println("Contato cadastrado com sucesso!");
		} else {
			System.out.println("Falha ao cadastrar contato.");
		}
		*/
		
		System.out.println(controlador.obterContatos());
		
		controlador.fecharConexao();
	}

}
