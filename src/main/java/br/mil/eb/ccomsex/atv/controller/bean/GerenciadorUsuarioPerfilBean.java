package br.mil.eb.ccomsex.atv.controller.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.entity.StatusUsuario;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.service.GrupoService;
import br.mil.eb.ccomsex.atv.model.service.UsuarioService;
import br.mil.eb.ccomsex.atv.model.service.exception.NegocioException;
import br.mil.eb.ccomsex.atv.util.jsf.FacesUtil;

@Named
@ViewScoped
public class GerenciadorUsuarioPerfilBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuario;

	@Inject
	private GrupoService grupoService;

	private Grupo grupoSelecionado;

	private List<Grupo> grupos;

	public void inicializar() {
		if (this.usuario == null) {
			limpar();
		}
		carregarGrupo();
	}

	public void limpar() {
		this.usuario = new Usuario();
	}

	public void carregarGrupo() {
		this.grupos = grupoService.listarTodos();

		for (Grupo g : this.usuario.getGrupos()) {
			this.grupos.remove(g);
		}
	}

	public StatusUsuario[] getStatusUsuario() {
		return StatusUsuario.values();
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public void adicionarPerfil() {
		usuario.getGrupos().add(grupoSelecionado);
		grupos.remove(grupoSelecionado);
	}

	public void excluirPerfil() {
		usuario.getGrupos().remove(grupoSelecionado);
		grupos.add(grupoSelecionado);
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