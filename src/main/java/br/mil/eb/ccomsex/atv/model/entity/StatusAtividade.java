package br.mil.eb.ccomsex.atv.model.entity;

public enum StatusAtividade {

	ADIADA("Adiada"), 
	AGUARDANDO("Aguardando"), 
	CONCLUIDA("Concluída"), 
	INICIADA("Iniciada"), 
	NAO_INICIADA("Não iniciada");

	private String descricao;

	StatusAtividade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
