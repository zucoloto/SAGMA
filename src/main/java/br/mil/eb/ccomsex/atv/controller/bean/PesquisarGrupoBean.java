package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.service.GrupoService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisarGrupoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoService grupoService;

	private Grupo grupoSelecionado;

	private List<Grupo> grupos;

	public void pesquisar() {
		this.grupos = grupoService.listarTodos();
	}

	public void excluir() {
		try {
			grupoService.excluir(grupoSelecionado);
			this.grupos.remove(grupoSelecionado);
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_excluido"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		}
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

}
