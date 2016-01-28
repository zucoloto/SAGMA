package br.mil.eb.ccomsex.atv.security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.util.jsf.UsuarioLogado;

@Named
@RequestScoped
public class UsuarioSistemaBean {

	@Inject
	private ExternalContext externalContext;

	UsuarioSistema usuarioLogado = getUsuarioLogado();

	public String getIdentidadeUsuario() {
		String identidadeUsuario = null;

		if (usuarioLogado != null) {
			identidadeUsuario = usuarioLogado.getUsuario().getIdentidade();
		}

		return identidadeUsuario;
	}

	public String getNomeUsuario() {
		String nome = null;

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNomeUsuario();
		}

		return nome;
	}

	@Produces
	@SessionScoped
	@UsuarioLogado
	private Usuario getUsuarioSistema() {
		Usuario usuario = null;

		if (usuarioLogado != null) {
			usuario = usuarioLogado.getUsuario();
		}
		return usuario;
	}

	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuarioSistema = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuarioSistema = (UsuarioSistema) auth.getPrincipal();
		}

		return usuarioSistema;
	}

	public boolean isFiltrarAtividadadPorFracao() {
		return externalContext.isUserInRole("ADM_SISTEMA") || externalContext.isUserInRole("ADM_OM");
	}

}
