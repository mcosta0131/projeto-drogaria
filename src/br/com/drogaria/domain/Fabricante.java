package br.com.drogaria.domain;

public class Fabricante {
	private Long codigo;
	private String descricao;

	// getters and setters
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		String saida = "Codigo : " + codigo + " --- Descrição : " + descricao;
		return saida;
	}
}
