package teste;

import java.util.Scanner;

import modelo.Banco;
import modelo.Conta;
import modelo.BancoCentral;

public class TesteBanco {
	static BancoCentral bancoCentral = new BancoCentral();
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Menu");
		System.out.println("1 - Criar Conta");
		System.out.println("2 - Depositar ");
		System.out.println("3 - Emitir saldo");
		System.out.println("4 - Sacar");
		System.out.println("5 - Transferir");
		System.out.println("6 - Emitir extrato");
		System.out.println("7 - Criar banco");
		System.out.println("8 - Enviar DOC");
		Integer menu = entrada.nextInt();
		operacao(menu);
	}

	private static void operacao(Integer menu) {
		while (menu != 0) {
			Scanner teclado = new Scanner(System.in);
			if (menu == 1) {
				System.out.println("Informe o banco da conta");
				Integer bancoConta = entrada.nextInt();
				Banco banco = bancoCentral.validarBanco(bancoConta);
				System.out.println("Informe o tipo da conta");
				System.out.println("1-Simples 2-Especial");
				String numero = String.valueOf(banco.getContas().size() + 1);
				String status = entrada.nextLine();
				banco.novaConta(numero, status.equals("2") ? "Especial" : "Simples", 0.0, 50.0);
			} else if (menu == 2) {
				System.out.println("Informe o numero do banco");
				Integer bancoConta = entrada.nextInt();
				Banco banco = bancoCentral.validarBanco(bancoConta);
				System.out.println("Informe o numero da conta");
				System.out.println("Informe o valor");
				String numeroConta = entrada.next();
				Double valorD = entrada.nextDouble();
				if (banco.contaExistente(numeroConta)) {
					Conta contaDeposito = banco.validarConta(numeroConta);
					contaDeposito.depositar(valorD);
				}

			} else if (menu == 3) {
				System.out.println("Informe o banco da conta");
				Integer bancoConta = entrada.nextInt();
				Banco banco = bancoCentral.validarBanco(bancoConta);
				System.out.println("Informe o numero da conta");
				String numeroConta = entrada.next();
				if (banco.contaExistente(numeroConta)) {
					Conta contaDeposito = banco.validarConta(numeroConta);
					contaDeposito.emitirSaldo();
				}

			} else if (menu == 4) {
				System.out.println("Informe o banco da conta");
				Integer bancoConta = entrada.nextInt();
				Banco banco = bancoCentral.validarBanco(bancoConta);
				System.out.println("Informe o numero da conta");
				System.out.println("Informe o valor");
				String numeroConta = entrada.next();
				Double valor = entrada.nextDouble();
				if (banco.contaExistente(numeroConta)) {
					Conta contaDeposito = banco.validarConta(numeroConta);
					if (contaDeposito.sacar(valor)) {
						System.out.println("Saque realizado..");
					}
				}
			} else if (menu == 5) {
				System.out.println("Informe o banco da conta ");
				Integer bancoConta = entrada.nextInt();
				Banco banco = bancoCentral.validarBanco(bancoConta);
				System.out.println("Informe o numero da conta T");
				System.out.println("Informe o numero da conta R");
				System.out.println("Informe o valor");
				String numeroContaT = entrada.next();
				String numeroContaR = entrada.next();
				Double valor = entrada.nextDouble();
				banco.transferir(numeroContaT, valor, numeroContaR);

			} else if (menu == 6) {
				System.out.println("Informe o banco da conta");
				Integer bancoConta = entrada.nextInt();
				Banco banco = bancoCentral.validarBanco(bancoConta);
				System.out.println("informe o numero da conta");
				String numeroConta = entrada.next();
				if (banco.contaExistente(numeroConta)) {
					Conta contaDeposito = banco.validarConta(numeroConta);
					contaDeposito.emitirExtrato();
				}
			}else if (menu == 7) {
				System.out.println("Informe o nome do banco");
				String nomeBanco = entrada.next();
				bancoCentral.criarBanco(nomeBanco);
				
			} else if (menu == 8) {
			System.out.println("Informe o numero do banco ENVIAR");
			Integer numeroBancoEnviar = teclado.nextInt();
			System.out.println("Informe o numero da conta ENVIAR");
			String numeroContaEnviar = teclado.next();
			
			System.out.println("Informe o numero do banco RECEBER");
			Integer numeroBancoReceber = teclado.nextInt();
			System.out.println("Informe o numero da conta RECEBER");
			String numeroContaReceber = teclado.next();
		
			System.out.println("Informe o Valor R$");
			Double valor = teclado.nextDouble();
			
			bancoCentral.realizarDoc(numeroBancoEnviar, numeroContaEnviar, numeroBancoReceber, numeroContaReceber, valor);
			
		}
		menu = entrada.nextInt();
		}
	}
}






