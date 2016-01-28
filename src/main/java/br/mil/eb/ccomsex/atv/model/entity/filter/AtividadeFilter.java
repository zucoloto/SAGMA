package br.mil.eb.ccomsex.atv.model.entity.filter;

import java.io.Serializable;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.StatusAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;

public class AtividadeFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	// private Long id;
	private AssuntoAtividade assuntoAtividade;
	private Usuario usuario;
	// private StatusPrioridade statusPrioridade;
	private StatusAtividade statusAtividade;

	// private Date prazo;
	// private Date dataInicio;
	// private Date dataTermino;
	// private String observacao;
	
	public AssuntoAtividade getAssuntoAtividade() {
		return assuntoAtividade;
	}

	public void setAssuntoAtividade(AssuntoAtividade assuntoAtividade) {
		this.assuntoAtividade = assuntoAtividade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusAtividade getStatusAtividade() {
		return statusAtividade;
	}

	public void setStatusAtividade(StatusAtividade statusAtividade) {
		this.statusAtividade = statusAtividade;
	}

}
