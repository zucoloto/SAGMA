package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.service.FracaoService;
import br.mil.eb.ccomsex.atv.model.service.UsuarioService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;
import br.mil.eb.ccomsex.atv.util.jsf.UsuarioLogado;

@Named
@SessionScoped
public class UsuarioLogadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuarioSelecionado;

	@Inject
	private FracaoService fracaoService;

	private Fracao fracaoSelecionado;

	private List<Fracao> fracaos;

	public void pesquisar() {
		this.usuarioLogado = usuarioService.buscarPorId(usuarioLogado.getId());
		carregarFracao();
	}

	public void carregarFracao() {
		this.fracaos = fracaoService.listarTodos();

		for (Fracao f : this.usuarioLogado.getFracoes()) {
			this.fracaos.remove(f);
		}
	}

	public void salvar() {
		try {
			usuarioLogado = usuarioService.salvar(this.usuarioLogado);
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public void adicionarFracao() {
		usuarioLogado.getFracoes().add(fracaoSelecionado);
		fracaos.remove(fracaoSelecionado);
	}

	public void excluirFracao() {
		usuarioLogado.getFracoes().remove(fracaoSelecionado);
		fracaos.add(fracaoSelecionado);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public Fracao getFracaoSelecionado() {
		return fracaoSelecionado;
	}

	public void setFracaoSelecionado(Fracao fracaoSelecionado) {
		this.fracaoSelecionado = fracaoSelecionado;
	}

	public List<Fracao> getFracaos() {
		return fracaos;
	}

}
