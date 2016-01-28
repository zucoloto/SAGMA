package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.Atividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.StatusAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.entity.filter.AtividadeFilter;
import br.mil.eb.ccomsex.atv.model.entity.filter.FracaoFilter;
import br.mil.eb.ccomsex.atv.model.service.AtividadeService;
import br.mil.eb.ccomsex.atv.model.service.FracaoService;
import br.mil.eb.ccomsex.atv.util.jsf.UsuarioLogado;

@Named
@ViewScoped
public class PesquisarAtividadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeService atividadeService;

	private AtividadeFilter filtroAtividade;

	private List<Atividade> atividadesFiltradas;

	@Inject
	private FracaoService fracaoService;

	private FracaoFilter filtroFracao;

	private List<Fracao> fracoes;

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	@Inject
	private ExternalContext externalContext;

	public void inicializar() {
		this.filtroAtividade = new AtividadeFilter();
		this.filtroFracao = new FracaoFilter();
		carregarFracoes();
		pesquisar();

		/*
		 * this.filtroAtividade.setUsuario(usuarioLogado);
		 * this.filtroAtividade.setStatusAtividade(StatusAtividade.INICIADA);;
		 */
	}

	public void pesquisar() {
		this.filtroAtividade.setUsuario(usuarioLogado);

		if (filtroAtividade.getStatusAtividade() != null) {
			System.out.println("Atividade NotNull: " + filtroAtividade.getStatusAtividade());
		} else {
			System.out.println("Atividade Null: " + filtroAtividade.getStatusAtividade());
		}

		if (filtroFracao.getId() == null) {
			System.out.println("Fração Null");
			// System.out.println("Fração Null: " +
			// filtroFracao.getNomeFracao());
		} else {
			System.out.println("Fração NotNull");
			System.out.println("Fração NotNull: " + filtroFracao.getId());
		}

		/*
		 * if (filtroFracao == null || filtroFracao.getId() == null) {
		 * System.out.println("Fração Null: " + filtroFracao.getNomeFracao()); }
		 * 
		 * if (filtroFracao != null || filtroFracao.getId() != null) {
		 * System.out.println("Fração NotNull: " +
		 * filtroFracao.getNomeFracao()); }
		 */

		this.atividadesFiltradas = atividadeService.listarPorCriterio(this.filtroAtividade, this.filtroFracao);
	}

	public void carregarFracoes() {
		// this.fracoes = fracaoService.listarTodos();

		if (externalContext.isUserInRole("ADM_SISTEMA") || externalContext.isUserInRole("ADM_OM")) {
			this.fracoes = fracaoService.listarTodos();
		} else {
			this.fracoes = fracaoService.listarFracaoPai(usuarioLogado.getFracoes());
		}
	}

	public StatusAtividade[] getStatusAtividade() {
		return StatusAtividade.values();
	}

	public AtividadeFilter getFiltroAtividade() {
		return filtroAtividade;
	}

	public void setFiltroAtividade(AtividadeFilter filtroAtividade) {
		this.filtroAtividade = filtroAtividade;
	}

	public List<Atividade> getAtividadesFiltradas() {
		return atividadesFiltradas;
	}

	public void setAtividadesFiltradas(List<Atividade> atividadesFiltradas) {
		this.atividadesFiltradas = atividadesFiltradas;
	}

	public FracaoFilter getFiltroFracao() {
		return filtroFracao;
	}

	public void setFiltroFracao(FracaoFilter filtroFracao) {
		this.filtroFracao = filtroFracao;
	}

	public List<Fracao> getFracoes() {
		return fracoes;
	}

}
