package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.service.FracaoService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GerenciadorFracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FracaoService fracaoService;

	private Fracao fracao;

	private List<Fracao> fracoes;

	public void inicializar() {
		if (this.fracao == null) {
			limpar();
		}
		carregarFracoes();
	}

	public void limpar() {
		this.fracao = new Fracao();
	}

	public void carregarFracoes() {
		this.fracoes = fracaoService.listarTodos();
	}

	public boolean isEditando() {
		return this.fracao.getId() != null;
	}

	public void salvar() {
		try {
			fracaoService.salvar(this.fracao);
			limpar();
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public Fracao getFracao() {
		return fracao;
	}

	public void setFracao(Fracao fracao) {
		this.fracao = fracao;
	}

	public List<Fracao> getFracoes() {
		return fracoes;
	}

}