package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.service.GrupoService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GerenciadorGrupoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoService grupoService;

	private Grupo grupo;

	public void inicializar() {
		if (this.grupo == null) {
			limpar();
		}
	}

	public void limpar() {
		this.grupo = new Grupo();
	}

	public boolean isEditando() {
		return this.grupo.getId() != null;
	}

	public void salvar() {
		try {
			grupoService.salvar(this.grupo);
			limpar();
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}