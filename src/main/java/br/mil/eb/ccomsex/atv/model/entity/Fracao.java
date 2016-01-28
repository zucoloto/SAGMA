package br.mil.eb.ccomsex.atv.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "atv_fracao")
public class Fracao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "nome_fracao")
	private String nomeFracao;

	@ManyToOne
	@JoinColumn(name = "fracao_pai_id")
	private Fracao fracaoPaiId;

	@OneToMany(mappedBy = "fracaoPaiId", cascade = CascadeType.ALL)
	@OrderBy("ordemQC ASC")
	private List<Fracao> subFracoes = new ArrayList<>();

	@Column(name = "ordem_qc")
	private String ordemQC;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 100)
	public String getNomeFracao() {
		return nomeFracao;
	}

	public void setNomeFracao(String nomeFracao) {
		this.nomeFracao = nomeFracao;
	}

	public Fracao getFracaoPaiId() {
		return fracaoPaiId;
	}

	public void setFracaoPaiId(Fracao fracaoPaiId) {
		this.fracaoPaiId = fracaoPaiId;
	}

	public List<Fracao> getSubFracoes() {
		return subFracoes;
	}

	public void setSubFracoes(List<Fracao> subFracoes) {
		this.subFracoes = subFracoes;
	}

	@NotBlank
	@Size(max = 10)
	public String getOrdemQC() {
		return ordemQC;
	}

	public void setOrdemQC(String ordemQC) {
		this.ordemQC = ordemQC;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Fracao other = (Fracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
