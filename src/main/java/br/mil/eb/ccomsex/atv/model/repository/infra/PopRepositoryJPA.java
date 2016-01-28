package br.mil.eb.ccomsex.atv.model.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.mil.eb.ccomsex.atv.model.entity.Pop;
import br.mil.eb.ccomsex.atv.model.repository.PopRepository;

public class PopRepositoryJPA implements PopRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@Override
	public Pop salvar(Pop pop) {
		return entityManager.merge(pop);
	}

	@Override
	public void excluir(Pop pop) {
		entityManager.remove(pop);
		entityManager.flush();
	}

	@Override
	public Pop buscarPorId(Long id) {
		return entityManager.find(Pop.class, id);
	}

	@Override
	public List<Pop> listarTodos() {
		return entityManager.createQuery("from Pop", Pop.class).getResultList();
	}

}
