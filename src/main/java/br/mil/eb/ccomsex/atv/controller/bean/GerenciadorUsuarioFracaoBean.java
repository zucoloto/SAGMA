package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.StatusUsuario;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.service.FracaoService;
import br.mil.eb.ccomsex.atv.model.service.UsuarioService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GerenciadorUsuarioFracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuario;

	@Inject
	private FracaoService fracaoService;

	private Fracao fracaoSelecionado;

	private List<Fracao> fracaos;

	public void inicializar() {
		if (this.usuario == null) {
			limpar();
		}
		carregarFracao();
	}

	public void limpar() {
		this.usuario = new Usuario();
	}

	public void carregarFracao() {
		this.fracaos = fracaoService.listarTodos();

		for (Fracao f : this.usuario.getFracoes()) {
			this.fracaos.remove(f);
		}
	}

	public StatusUsuario[] getStatusUsuario() {
		return StatusUsuario.values();
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public void adicionarFracao() {
		usuario.getFracoes().add(fracaoSelecionado);
		fracaos.remove(fracaoSelecionado);
	}

	public void excluirFracao() {
		usuario.getFracoes().remove(fracaoSelecionado);
		fracaos.add(fracaoSelecionado);
	}

	public void salvar() {
		try {
			usuarioService.salvar(this.usuario);
			// limpar();
			FacesUtil.addInfoMessage(FacesUtil.getMensagemI18n("registro_salvo"));
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(FacesUtil.getMensagemI18n(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addFatalMessage(FacesUtil.getMensagemI18n("contato_administrador"));
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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