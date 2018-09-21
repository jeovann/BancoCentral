package modelo;

import java.util.ArrayList;
import java.util.List;

public class Conta {
	private String numero;
	private Double saldo;
	private String status;
	private Double limite;
	private List<Movimentacao> listaMovimentacao = new ArrayList<>();

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public List<Movimentacao> getListaMovimentacao() {
		return listaMovimentacao;
	}

	public void setListaMovimentacao(List<Movimentacao> listaMovimentacao) {
		this.listaMovimentacao = listaMovimentacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	// Verifica se conta está negativa
	public Boolean contaNegativa() {
		return saldo < 0.0 ? Boolean.TRUE:Boolean.FALSE;
	}

	public Boolean sacar(double valor) {
		if (valor <= (limite + saldo)) {
			saldo = saldo - valor;
			Movimentacao movimentacao = new Movimentacao("Saque", 
					valor, "Débito");
			adicionarMovimentacao(movimentacao);
			return Boolean.TRUE;
		} else {
			System.out.println("Limite insulficiente, "
					 + (saldo + limite));
		}
		return Boolean.FALSE;
	}
	
	public void depositar(double valor) {		
			saldo = saldo + valor;
			Movimentacao movimentacao = new Movimentacao("Deposito", 
					valor, "Crédito");
			adicionarMovimentacao(movimentacao);
			System.out.println("Deposito realizado com sucesso...");
	}

	public void emitirSaldo() {
		System.out.println("Emimssão de saldo da conta "+numero);
		System.out.println("Saldo atual é " + saldo);
		System.out.println("Seu limite atual é " + limite);
		System.out.println("Saldo + Limite = " + (saldo+limite));
	}

	public void credito(String descricao, double valorTransferencia) {
		saldo=saldo+valorTransferencia;
		Movimentacao movimentacao = new Movimentacao(descricao, 
				valorTransferencia, "Crédito");
		adicionarMovimentacao(movimentacao);
	}

	public void debito(String descricao, double valorTransferencia) {
		saldo=saldo-valorTransferencia;
		Movimentacao movimentacao = new Movimentacao(descricao, 
				valorTransferencia, "Débito");
		adicionarMovimentacao(movimentacao);
	}

	private void adicionarMovimentacao(Movimentacao movimentacao) {
		listaMovimentacao.add(movimentacao);
	}
	
	public void emitirExtrato() {
		listaMovimentacao.stream().forEach(mv->{System.out.println(mv);});
	}
	
	@Override
	public String toString() {
		return "Conta : nº "+numero;
	}

	public double totalSaldo() {
		return saldo+limite;
	}
	
	public void receberDoc(String descricao, Double valor) {
		saldo=saldo+valor;
		Movimentacao movimentacao = new Movimentacao(descricao, valor, "Crédito");
		adicionarMovimentacao(movimentacao);
	}
	
	public void enviarDoc(String descricao, Double valor) {
		saldo=saldo-valor;
		Movimentacao movimentacao = new Movimentacao(descricao, valor, "Crédito");
		adicionarMovimentacao(movimentacao);
	}

}
