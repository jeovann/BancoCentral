package modelo;

public class Movimentacao {
	
	private String descricao;
	private Double valor;
	private String tipo;
	
	public Movimentacao(String descricao,Double valor, String tipo) {
		this.descricao=descricao;
		this.valor=valor;
		this.tipo=tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Movimentacao [descricao=" + descricao + ", valor=" + valor + ", tipo=" + tipo + "]";
	}
	
	

}
