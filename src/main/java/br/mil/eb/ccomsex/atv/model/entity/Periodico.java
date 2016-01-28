package br.mil.eb.ccomsex.atv.model.entity;

public enum Periodico {

	EXPORADICO("Exporádico"), 
	DIARIO("Diário"), 
	SEMANAL("Semanal"), 
	QUINZENAL("Quinzenal"), 
	MENSAL("Mensal"), 
	BIMESTRAL("Bimestral"), 
	TRIMESTRAL("Trimestral"), 
	SEMESTRAL("Semestral"), 
	ANUAL("Anual");

	private String descricao;

	Periodico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
