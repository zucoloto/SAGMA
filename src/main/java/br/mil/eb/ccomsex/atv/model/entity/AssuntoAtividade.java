package br.mil.eb.ccomsex.atv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "atv_assunto_atividade")
public class AssuntoAtividade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fracao_id")
	private Fracao fracao;

	@Column(name = "nome_atividade")
	private String nomeAtividade;

	@Column(name = "caderno_encargo")
	private Boolean cadernoEncargo;

	@Enumerated(EnumType.STRING)
	private Periodico periodico;

	@OneToOne(mappedBy = "assuntoAtividade")
	private Pop pop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	public Fracao getFracao() {
		return fracao;
	}

	public void setFracao(Fracao fracao) {
		this.fracao = fracao;
	}

	@NotBlank
	@Size(max = 100)
	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public Boolean getCadernoEncargo() {
		return cadernoEncargo;
	}

	public void setCadernoEncargo(Boolean cadernoEncargo) {
		this.cadernoEncargo = cadernoEncargo;
	}

	@NotNull
	public Periodico getPeriodico() {
		return periodico;
	}

	public void setPeriodico(Periodico periodico) {
		this.periodico = periodico;
	}

	public Pop getPop() {
		return pop;
	}

	public void setPop(Pop pop) {
		this.pop = pop;
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
		AssuntoAtividade other = (AssuntoAtividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
