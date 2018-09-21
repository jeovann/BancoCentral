package modelo;

import java.util.ArrayList;
import java.util.List;

public class BancoCentral {
	private String pais;
	private List<Banco> bancos = new ArrayList<>();
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

	public Boolean bancosExistente(Integer codigo) {
		Banco bancosComparar = new Banco();
		bancosComparar.setCodigo(codigo);
		return bancos.contains(bancosComparar);
	}

	public Banco validarBanco(Integer codigo) {
		if(bancosExistente(codigo)) {
			Banco bancoEncontrado = new Banco();
			bancoEncontrado.setCodigo(codigo);
			return bancoEncontrado = bancos.get(bancos.indexOf(bancoEncontrado));
		}
		return null;
	}
	
	public void criarBanco(String nome) {
		Banco banco = new Banco();
		banco.setNome(nome);
		banco.setCodigo(bancos.size()+1);
		bancos.add(banco);
		System.out.println("Banco criado com sucesso...");
		System.out.println(banco.toString());
				
	}
	
	public void realizarDoc(Integer codigoBanco, String contaBanco, Integer codigoBancoReceber, String contaReceber, Double valor) {
		Banco banco = validarBanco(codigoBanco);
		if(banco!=null) {
			Banco bancoReceber = validarBanco(codigoBancoReceber);
			if(bancoReceber!=null) {
				Boolean retorno = banco.enviarDoc(codigoBanco, contaBanco, valor, contaReceber);
				Boolean retornoRecebimento = bancoReceber.receberDoc(codigoBanco, contaBanco, valor, contaReceber);
				if(retorno) {
					System.out.println("Doc enviado com sucesso...");
				}else {
					System.out.println("Houve um erro no envio do doc");
				}
				
				if(retornoRecebimento) {
					System.out.println("Doc recebido com sucesso...");
				}else {
					System.out.println("Houve um erro no recebimento do doc");
				}
			}
		}
		
	}
}