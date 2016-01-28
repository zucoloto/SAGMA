package br.mil.eb.ccomsex.atv.model.entity.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;

public class FracaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomeFracao;
	private Fracao fracaoPaiId;
	private List<FracaoFilter> subFracoes = new ArrayList<>();
	private String ordemQC;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFracao() {
		return nomeFracao;
	}

	public void setNomeFracao(String nomeFracao) {
		this.nomeFracao = nomeFracao;
	}

	public Fracao getFracaoPaiId() {
		if (fracaoPaiId == null)
			fracaoPaiId = new Fracao();
		return fracaoPaiId;
	}

	public void setFracaoPaiId(Fracao fracaoPaiId) {
		this.fracaoPaiId = fracaoPaiId;
	}

	public List<FracaoFilter> getSubFracoes() {
		return subFracoes;
	}

	public void setSubFracoes(List<FracaoFilter> subFracoes) {
		this.subFracoes = subFracoes;
	}

	public String getOrdemQC() {
		return ordemQC;
	}

	public void setOrdemQC(String ordemQC) {
		this.ordemQC = ordemQC;
	}

}
