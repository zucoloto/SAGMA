package br.mil.eb.ccomsex.atv.model.entity;

public enum StatusPrioridade {

	ALTA("Alta"), 
	NORMAL("Normal"), 
	BAIXA("Baixa");

	private String descricao;

	StatusPrioridade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
