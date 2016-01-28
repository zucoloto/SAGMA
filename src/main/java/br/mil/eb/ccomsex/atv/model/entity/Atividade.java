package br.mil.eb.ccomsex.atv.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "atv_atividade")
public class Atividade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "assunto_atividade_id")
	private AssuntoAtividade assuntoAtividade;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_prioridade")
	private StatusPrioridade statusPrioridade;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_atividade")
	private StatusAtividade statusAtividade;

	@Temporal(TemporalType.DATE)
	@Column(name = "prazo")
	private Date prazo;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino")
	private Date dataTermino;

	@Column(columnDefinition = "TEXT")
	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	public AssuntoAtividade getAssuntoAtividade() {
		return assuntoAtividade;
	}

	public void setAssuntoAtividade(AssuntoAtividade assuntoAtividade) {
		this.assuntoAtividade = assuntoAtividade;
	}

	@NotNull
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@NotNull
	public StatusPrioridade getStatusPrioridade() {
		return statusPrioridade;
	}

	public void setStatusPrioridade(StatusPrioridade prioridade) {
		this.statusPrioridade = prioridade;
	}

	@NotNull
	public StatusAtividade getStatusAtividade() {
		return statusAtividade;
	}

	public void setStatusAtividade(StatusAtividade statusAtividade) {
		this.statusAtividade = statusAtividade;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
