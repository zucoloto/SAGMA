package br.mil.eb.ccomsex.atv.model.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.mil.eb.ccomsex.atv.model.entity.Configurar;
import br.mil.eb.ccomsex.atv.model.repository.ConfigurarRepository;

public class ConfigurarRepositoryJPA implements ConfigurarRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@Override
	public void salvar(Configurar configurar) {
		entityManager.merge(configurar);
	}

	@Override
	public Configurar buscarPorId(Long id) {
		return entityManager.find(Configurar.class, id);
	}

	@Override
	public List<Configurar> listarTodos() {
		return entityManager.createQuery("from Configurar", Configurar.class).getResultList();
	}

}
