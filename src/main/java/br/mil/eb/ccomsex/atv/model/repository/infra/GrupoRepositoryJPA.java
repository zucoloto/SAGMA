package br.mil.eb.ccomsex.atv.model.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.repository.GrupoRepository;

public class GrupoRepositoryJPA implements GrupoRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@Override
	public void salvar(Grupo grupo) {
		entityManager.merge(grupo);
	}

	@Override
	public void excluir(Grupo grupo) {
		entityManager.remove(grupo);
		entityManager.flush();
	}

	@Override
	public Grupo buscarPorId(Long id) {
		return entityManager.find(Grupo.class, id);
	}

	@Override
	public List<Grupo> listarTodos() {
		return entityManager.createQuery("from Grupo", Grupo.class).getResultList();
	}

}