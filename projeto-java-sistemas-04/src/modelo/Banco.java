package modelo;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private Integer codigo;
	private String nome;
	private List<Conta> contas = new ArrayList<>();


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	
	@Override
	public String toString() {
		return "Banco [codigo=" + codigo + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Banco other = (Banco) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public Boolean contaExistente(String numero) {
	Conta contaComparar = new Conta();
	contaComparar.setNumero(numero);
	return contas.contains(contaComparar);
	}

	public void novaConta(String numero, String status, Double saldo, Double limite) {
		Conta contaNova = new Conta();
		contaNova.setNumero(numero);
		contaNova.setLimite(limite);
		contaNova.setSaldo(saldo);
		contaNova.setStatus(status);
		contas.add(contaNova);
		System.out.println(contaNova);
	}

	// Método responsável por excluir contas do Banco
	public void excluirConta(String numero) {
		if (contaExistente(numero)) {
			Conta contaExcluir = new Conta();
			contaExcluir.setNumero(numero);
			contaExcluir = contas.get(contas.indexOf(contaExcluir));
			if (contaExcluir.contaNegativa()) {
				System.err.println("Conta com saldo negativo");
			} else {
				contas.remove(contaExcluir);
			}
		} else {
			System.out
					.println("A conta com o número " + numero + " não existe, " + "por esse motivo não foi excluida!");
		}
	}

	public Conta validarConta(String numero) {
		if (contaExistente(numero)) {
			Conta contaEncontrada = new Conta();
			contaEncontrada.setNumero(numero);
			return contaEncontrada = contas.get(contas.indexOf(contaEncontrada));
		}
		return null;
	}

	public void transferir(String nContaTransferir, double valorTransferencia, String nContaReceber) {
		Conta contaTransferir = validarConta(nContaTransferir);
		if (contaTransferir != null) {
			if (contaTransferir.totalSaldo() >= valorTransferencia) {
				Conta contaReceber = validarConta(nContaReceber);
				if (contaReceber != null) {
					contaReceber.credito("Crédito Trans. "+contaTransferir.getNumero(),valorTransferencia);
					contaTransferir.debito("Débito Trans. "+contaTransferir.getNumero(),valorTransferencia);
					atualizarConta(contaReceber);
					atualizarConta(contaTransferir);
					System.out.println("Transferêcia realizada....");
				}else {
					System.out.println("Conta para receber a transferência "+nContaTransferir+" não existe!");
				}
			}else {
				System.out.println("Saldo insulficiente");
			}
		}else {
			System.out.println("Conta "+nContaTransferir+" não existe!");
		}
	}

	private void atualizarConta(Conta contaReceber) {
		contas.set(contas.indexOf(contaReceber), contaReceber);		
	}

	public Boolean receberDoc(Integer codigoBanco, String conta, Double valor, String contaReceber) {
		Conta contaBanco = validarConta(contaReceber);
		if (contaBanco != null) {
			contaBanco.receberDoc("Doc recebido do banco "+codigoBanco+", conta "+conta+" valor : "+valor, valor);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
	public Boolean enviarDoc(Integer codigoBanco, String conta, Double valor, String contaReceber) {
		Conta contaBanco = validarConta(contaReceber);
		if (contaBanco != null) {
			contaBanco.enviarDoc("Doc enviado para o banco "+codigoBanco+", conta "+conta+" valor : "+valor, valor);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}	