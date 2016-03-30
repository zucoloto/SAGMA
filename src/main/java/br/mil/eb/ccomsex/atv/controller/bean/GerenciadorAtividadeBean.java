package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Atividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.StatusAtividade;
import br.mil.eb.ccomsex.atv.model.entity.StatusPrioridade;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.service.AssuntoAtividadeService;
import br.mil.eb.ccomsex.atv.model.service.AtividadeService;
import br.mil.eb.ccomsex.atv.model.service.UsuarioService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;
import br.mil.eb.ccomsex.atv.util.jsf.UsuarioLogado;

@Named
@ViewScoped
public class GerenciadorAtividadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeService atividadeService;

	private Atividade atividade;

	@Inject
	private AssuntoAtividadeService assuntoAtividadeService;

	private List<AssuntoAtividade> assuntoAtividades;

	// @Inject
	// private FracaoService fracaoService;
	//
	// private List<Fracao> fracoes = new ArrayList<>();

	@Inject
	private UsuarioService usuarioService;

	private List<Usuario> usuarios;

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	private Usuario usuario;

	public void inicializar() {
		if (this.atividade == null) {
			limpar();
		}
		carregarAssuntoAtividade();
		carregarUsuario();
	}

	public void limpar() {
		this.atividade = new Atividade();
	}

	public void carregarAssuntoAtividade() {
		List<Fracao> fracoes = new ArrayList<>();

		fracoes.add(usuarioLogado.getFracoes().get(0).getFracaoPaiId());
		fracoes.add(usuarioLogado.getFracoes().get(0));

		this.assuntoAtividades = assuntoAtividadeService.listarPorFracao(fracoes);

		// assuntoAtividades = assuntoAtividadeService.listarTodos();
		// this.assuntoAtividades =
		// assuntoAtividadeService.listarPorFracao(usuarioLogado.getFracoes());
	}

	public void carregarUsuario() {
		this.usuario = usuarioService.buscarPorId(usuarioLogado.getId());

		if (isEditando()) {
			this.usuarios = usuarioService.listarTodos();
		} else {
			this.atividade.setUsuario(this.usuario);
		}
	}

	public StatusPrioridade[] getStatusPrioridade() {
		return StatusPrioridade.values();
	}

	public StatusAtividade[] getStatusAtividade() {
		return StatusAtividade.values();
	}

	public Date getDataHoje() {
		return new Date();
	}

	public boolean isEditando() {
		return this.atividade.getId() != null;
	}

	public void salvar() {
		try {
			atividadeService.salvar(this.atividade);
			limpar();
			carregarUsuario();
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<AssuntoAtividade> getAssuntoAtividades() {
		return assuntoAtividades;
	}

	// public List<Fracao> getFracoes() {
	// return fracoes;
	// }

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}