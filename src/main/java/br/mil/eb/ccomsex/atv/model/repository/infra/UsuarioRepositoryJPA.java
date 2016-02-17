package br.mil.eb.ccomsex.atv.model.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.repository.UsuarioRepository;

public class UsuarioRepositoryJPA implements UsuarioRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@Override
	public Usuario salvar(Usuario usuario) {
		usuario = entityManager.merge(usuario);
		return usuario;
	}

	@Override
	public void excluir(Usuario usuario) {
		entityManager.remove(usuario);
		entityManager.flush();
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> listarTodos() {
		return entityManager.createQuery("from Usuario order by nomeUsuario", Usuario.class).getResultList();
	}

	@Override
	public Usuario buscarPorNome(String nome) {
		Usuario usuario = null;

		try {
			usuario = entityManager.createQuery("from Usuario where upper(nomeUsuario) = :pNome", Usuario.class)
					.setParameter("pNome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado
		}

		return usuario;
	}

	@Override
	public Usuario buscarPorIdentidade(String identidade) {
		Usuario usuario = null;

		try {
			usuario = entityManager.createQuery("from Usuario where identidade = :pIdentidade", Usuario.class)
					.setParameter("pIdentidade", identidade).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado
		}

		return usuario;
	}

	@Override
	public Usuario buscarPorNomeSenha(String nome, String senha) {
		try {
			Query query = entityManager
					.createQuery("from Usuario where upper(nomeUsuario) = :pNome and senha = :pSenha", Usuario.class);
			query.setParameter("pNome", nome.toUpperCase());
			query.setParameter("pSenha", senha);
			return (Usuario) query.getSingleResult();
		} catch (NullPointerException e) {
			return null;
		}
	}

}