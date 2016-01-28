package br.mil.eb.ccomsex.atv.model.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.repository.AssuntoAtividadeRepository;

public class AssuntoAtividadeRepositoryJPA implements AssuntoAtividadeRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@Override
	public AssuntoAtividade salvar(AssuntoAtividade assuntoAtividade) {
		return entityManager.merge(assuntoAtividade);
	}

	@Override
	public void excluir(AssuntoAtividade assuntoAtividade) {
		entityManager.remove(assuntoAtividade);
		entityManager.flush();
	}

	@Override
	public AssuntoAtividade buscarPorId(Long id) {
		return entityManager.find(AssuntoAtividade.class, id);
	}

	@Override
	public List<AssuntoAtividade> listarTodos() {
		return entityManager.createQuery("from AssuntoAtividade", AssuntoAtividade.class).getResultList();
	}

	@Override
	public List<AssuntoAtividade> listarPorFracao(List<Fracao> fracoes) {
		String jpql = "select a from AssuntoAtividade a JOIN a.fracao f where f IN (:pFracao)";

		TypedQuery<AssuntoAtividade> query = entityManager.createQuery(jpql, AssuntoAtividade.class);
		query.setParameter("pFracao", fracoes);

		List<AssuntoAtividade> assuntos = query.getResultList();

		return assuntos;
	}

}
