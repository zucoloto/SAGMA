package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.service.AssuntoAtividadeService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;
import br.mil.eb.ccomsex.atv.util.jsf.UsuarioLogado;

@Named
@ViewScoped
public class PesquisarAssuntoAtividadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AssuntoAtividadeService assuntoAtividadeService;

	private AssuntoAtividade assuntoAtividadeSelecionado;

	private List<AssuntoAtividade> assuntoAtividades;

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	public void pesquisar() {
		this.assuntoAtividades = assuntoAtividadeService.listarPorFracao(usuarioLogado.getFracoes());
	}

	public void excluir() {
		try {
			assuntoAtividadeService.excluir(assuntoAtividadeSelecionado);
			this.assuntoAtividades.remove(assuntoAtividadeSelecionado);
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_excluido"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		}
	}

	public AssuntoAtividade getAssuntoAtividadeSelecionado() {
		return assuntoAtividadeSelecionado;
	}

	public void setAssuntoAtividadeSelecionado(AssuntoAtividade assuntoAtividadeSelecionado) {
		this.assuntoAtividadeSelecionado = assuntoAtividadeSelecionado;
	}

	public List<AssuntoAtividade> getAssuntoAtividades() {
		return assuntoAtividades;
	}

}
