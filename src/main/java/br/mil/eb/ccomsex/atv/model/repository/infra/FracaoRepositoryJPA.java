package br.mil.eb.ccomsex.atv.model.repository.infra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.repository.FracaoRepository;

public class FracaoRepositoryJPA implements FracaoRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@Override
	public void salvar(Fracao fracao) {
		entityManager.merge(fracao);
	}

	@Override
	public void excluir(Fracao fracao) {
		entityManager.remove(fracao);
		entityManager.flush();
	}

	@Override
	public Fracao buscarPorId(Long id) {
		return entityManager.find(Fracao.class, id);
	}

	@Override
	public List<Fracao> listarTodos() {
		return entityManager.createQuery("select f from Fracao f order by ordemQC", Fracao.class).getResultList();
	}

	@Override
	public List<Fracao> listarFracaoPai(List<Fracao> fracoes) {
		String jpql = "select f from Fracao f where f IN (:pFracoes) group by f.fracaoPaiId order by ordemQC";
		TypedQuery<Fracao> query = entityManager.createQuery(jpql, Fracao.class);
		query.setParameter("pFracoes", fracoes);
		List<Fracao> fracoesPai = query.getResultList();
		return listarFracaoFilhoPorUsuario(fracoesPai);
		
		

		/*
		 * String jpql =
		 * "select f from Fracao f where f IN (:pFracoes) order by ordemQC";
		 * 
		 * TypedQuery<Fracao> query = entityManager.createQuery(jpql,
		 * Fracao.class); query.setParameter("pFracoes", fracoes);
		 * 
		 * return query.getResultList();
		 */

		/*
		 * List<Fracao> resultado = new ArrayList<>();
		 * 
		 * for (Fracao f : fracoes) { System.out.println(
		 * "-------------------------------------------------");
		 * System.out.println(f.getFracaoPaiId().getOrdemQC() + " - " +
		 * f.getFracaoPaiId().getNomeFracao());
		 * 
		 * resultado.add(f);
		 * 
		 * Session session = this.entityManager.unwrap(Session.class); Criteria
		 * criteria = session.createCriteria(Fracao.class);
		 * 
		 * if (f.getNomeFracao().equals("Chefia") ||
		 * f.getNomeFracao().equals("Subchefia")) {
		 * criteria.add(Restrictions.eq("fracaoPaiId.id",
		 * f.getFracaoPaiId().getId())); } else {
		 * criteria.add(Restrictions.eq("id", f.getId())); }
		 * 
		 * List<Fracao> ffs = criteria.list(); for (Fracao ff : ffs) {
		 * System.out.println(ff.getOrdemQC() + " - " + ff.getNomeFracao()); } }
		 * 
		 * return null;
		 */
	}

	public List<Fracao> listarFracaoFilhoPorUsuario(List<Fracao> fracoesPai) {
		List<Fracao> resultado = new ArrayList<>();
		for (Fracao f : fracoesPai) {
			resultado.add(f.getFracaoPaiId());
			TypedQuery<Fracao> query = null;
			String jpql = null;
			jpql = "select f from Fracao f where f.fracaoPaiId.id = (:pFracoesF)";
			query = entityManager.createQuery(jpql, Fracao.class);
			query.setParameter("pFracoesF", f.getFracaoPaiId().getId());

			List<Fracao> fracoesF = query.getResultList();
			for (Fracao ff : fracoesF) {
				resultado.add(ff);
			}
		}
		return resultado;
	}

	@Override
	public List<Fracao> raizes() {
		return entityManager.createQuery("from Fracao where fracaoPaiId is null order by ordemQC", Fracao.class)
				.getResultList();
	}

}
