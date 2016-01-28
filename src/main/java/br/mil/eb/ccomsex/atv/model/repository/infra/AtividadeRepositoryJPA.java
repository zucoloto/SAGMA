package br.mil.eb.ccomsex.atv.model.repository.infra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.mil.eb.ccomsex.atv.model.entity.Atividade;
import br.mil.eb.ccomsex.atv.model.entity.filter.AtividadeFilter;
import br.mil.eb.ccomsex.atv.model.entity.filter.FracaoFilter;
import br.mil.eb.ccomsex.atv.model.repository.AtividadeRepository;

public class AtividadeRepositoryJPA implements AtividadeRepository, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@Override
	public void salvar(Atividade atividade) {
		entityManager.merge(atividade);
	}

	@Override
	public void excluir(Atividade atividade) {
		entityManager.remove(atividade);
		entityManager.flush();
	}

	@Override
	public Atividade buscarPorId(Long id) {
		return entityManager.find(Atividade.class, id);
	}

	@Override
	public List<Atividade> listarTodos() {
		return entityManager.createQuery("from Atividade", Atividade.class).getResultList();
	}

	/*
	 * @Override public List<Atividade> listarPorFracao(Long id) { String jpql =
	 * "select a from Atividade a JOIN a.assuntoAtividade f where f.fracao.id IN (:pFracao)"
	 * ;
	 * 
	 * TypedQuery<Atividade> query = entityManager.createQuery(jpql,
	 * Atividade.class); query.setParameter("pFracao", id);
	 * 
	 * List<Atividade> atividades = query.getResultList();
	 * 
	 * return atividades; }
	 */

	/*
	 * @Override public List<Atividade> listarPorFracao(List<Fracao> fracoes) {
	 * String jpql =
	 * "select a from Atividade a JOIN a.assuntoAtividade f where f.fracao IN (:pFracao)"
	 * ;
	 * 
	 * TypedQuery<Atividade> query = entityManager.createQuery(jpql,
	 * Atividade.class); query.setParameter("pFracao", fracoes);
	 * 
	 * List<Atividade> atividades = query.getResultList();
	 * 
	 * return atividades; }
	 */

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Atividade> listarPorFracao(List<Fracao> fracoes) {
	 * Session session = this.entityManager.unwrap(Session.class); Criteria
	 * criteria = session.createCriteria(Atividade.class);
	 * 
	 * criteria.createAlias("assuntoAtividade", "a");
	 * criteria.createAlias("a.fracao", "f");
	 * criteria.createAlias("f.fracaoPaiId", "fp");
	 * 
	 * criteria.add(Restrictions.eq("statusAtividade",
	 * StatusAtividade.INICIADA));
	 * 
	 * //criteria.add(Restrictions.in("f", fracoes));
	 * 
	 * //criteria.add(Restrictions.in("fp", usuario.getFracoes()));
	 * 
	 * List<Atividade> atividades = criteria.list();
	 * 
	 * for (Atividade a : atividades) { System.out.println(a.getId() + " - " +
	 * a.getAssuntoAtividade().getNomeAtividade()); }
	 * 
	 * return atividades; }
	 */

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Atividade> listarPorFracao(Long id) { Session
	 * session = this.entityManager.unwrap(Session.class); Criteria criteria =
	 * session.createCriteria(Atividade.class);
	 * 
	 * criteria.createAlias("assuntoAtividade", "a");
	 * criteria.createAlias("a.fracao", "f");
	 * criteria.createAlias("f.fracaoPaiId", "fp");
	 * 
	 * criteria.add(Restrictions.eq("statusAtividade",
	 * StatusAtividade.INICIADA));
	 * 
	 * //criteria.add(Restrictions.eq("id", id));
	 * 
	 * List<Atividade> atividades = criteria.list();
	 * 
	 * for (Atividade a : atividades) { System.out.println(a.getId() + " - " +
	 * a.getAssuntoAtividade().getNomeAtividade()); }
	 * 
	 * return atividades; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Atividade> listarPorCriterio(AtividadeFilter filtroAtividade, FracaoFilter filtroFracao) {
		Session session = this.entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Atividade.class);

		criteria.createAlias("usuario", "u");
		criteria.createAlias("assuntoAtividade", "a");
		criteria.createAlias("a.fracao", "f");
		criteria.createAlias("f.fracaoPaiId", "fp");

		if (filtroAtividade.getStatusAtividade() != null) {
			criteria.add(Restrictions.eq("statusAtividade", filtroAtividade.getStatusAtividade()));
		}
		
		if (filtroFracao.getId() == null) {
			criteria.add(Restrictions.eq("u.id", filtroAtividade.getUsuario().getId()));
		} else if (filtroFracao.getId() != 1L) {
			criteria.add(Restrictions.or(Restrictions.eq("f.id", filtroFracao.getId()),
					Restrictions.eq("fp.id", filtroFracao.getId())));
		} else {
			criteria.addOrder(Order.asc("f.id"));
		}
		
		/*if (filtroFracao.getId() != null) {
			if (filtroFracao.getFracaoPaiId().getId() != 1L) {
				criteria.add(Restrictions.eq("f.id", filtroFracao.getId()));
			}
		}*/
		

		/*if (filtroFracao != null && filtroFracao.getId() == null) {
			criteria.add(Restrictions.eq("u.id", filtroAtividade.getUsuario().getId()));
		}*/

		/*if (filtroFracao != null && filtroFracao.getId() != null) {
			if (filtroFracao.getFracaoPaiId().getId() != 1L) {
				criteria.add(Restrictions.eq("f.id", filtroFracao.getId()));
			} else {
				criteria.add(Restrictions.or(Restrictions.eq("f.id", filtroFracao.getId()),
						Restrictions.eq("fp.id", filtroFracao.getId())));
			}
		}*/

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Atividade> atividades = criteria.list();
		return atividades;
	}
}
