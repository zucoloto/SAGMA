package br.mil.eb.ccomsex.atv.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.repository.UsuarioRepository;
import br.mil.eb.ccomsex.atv.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String identidadeUsuario) throws UsernameNotFoundException {

		UsuarioRepository usuarioRepository = CDIServiceLocator.getBean(UsuarioRepository.class);
		Usuario usuario = usuarioRepository.buscarPorIdentidade(identidadeUsuario);

		UsuarioSistema usuarioSistema = null;

		if (usuario != null) {
			usuarioSistema = new UsuarioSistema(usuario, getGrupos(usuario));
		}

		return usuarioSistema;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNomeGrupo().toUpperCase()));
		}

		return authorities;
	}

}
