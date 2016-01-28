package br.mil.eb.ccomsex.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.Atividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.StatusAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.atv.model.entity.filter.AtividadeFilter;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class PesquisarAtividade {

	private EntityManager entityManager;

	@Before
	public void setUp() {
		entityManager = JPAUtil.createEntityManager();
	}

	@After
	public void tearDown() {
		entityManager.close();
	}

	@Test
	public void listarAtividade() {

		// criteria.add(Restrictions.in("f", usuario.getFracoes()));

		// criteria.add(Restrictions.eq("fp.id", 77L));

		// criteria.add(Restrictions.in("fp", usuario.getFracoes()));

		AtividadeFilter filtroAtividade = new AtividadeFilter();
		filtroAtividade.setStatusAtividade(StatusAtividade.CONCLUIDA);
		
		Fracao filtroFracao = new Fracao();
		filtroFracao = entityManager.find(Fracao.class, 3L);
		
		System.out.println(filtroFracao.getNomeFracao());
		
		List<Atividade> atividades = listarPorCriterio(filtroAtividade, filtroFracao);

		for (Atividade a : atividades) {
			System.out.println(a.getId() + " - " + a.getAssuntoAtividade().getNomeAtividade() + " - " + a.getAssuntoAtividade().getFracao().getNomeFracao());
		}
	}

	@SuppressWarnings("unchecked")
	private List<Atividade> listarPorCriterio(AtividadeFilter filtroAtividade, Fracao filtroFracao) {
		Session session = this.entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Atividade.class);
		
		criteria.createAlias("usuario", "u");
		criteria.createAlias("assuntoAtividade", "a");
		criteria.createAlias("a.fracao", "f");
		criteria.createAlias("f.fracaoPaiId", "fp");
		
		if (filtroAtividade.getStatusAtividade() != null) {
			criteria.add(Restrictions.eq("statusAtividade", filtroAtividade.getStatusAtividade()));
		}
		
		if (filtroFracao != null && filtroFracao.getId() != null) {
			if (filtroFracao.getFracaoPaiId().getId() != 1L) {
				criteria.add(Restrictions.eq("f.id", filtroFracao.getId()));
			} else {
				criteria.add(Restrictions.or(Restrictions.eq("f.id", filtroFracao.getId()),
						Restrictions.eq("fp.id", filtroFracao.getId())));
			}
		}
		
		/*if (filtroAtividade.getStatusAtividade() != null) {
			criteria.add(Restrictions.eq("statusAtividade", filtroAtividade.getStatusAtividade()));
		}
		
		if (filtroFracao != null) {
			criteria.add(Restrictions.eq("f.id", filtroFracao.getId()));
		}
		
		if (filtroFracao != null) {
			criteria.add(Restrictions.eq("fp.id", filtroFracao.getFracaoPaiId().getId()));
		}*/
		
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<Atividade> atividades = criteria.list();		
		return atividades;
	}

	@Test
	public void ExecutarPorObjeto() {
		Usuario usuario = new Usuario();
		usuario = entityManager.find(Usuario.class, 8L);

		List<Fracao> fracoes = new ArrayList<>();
		fracoes = usuario.getFracoes();

		String jpql = "select a from Atividade a JOIN a.assuntoAtividade f where f.fracao IN (:pFracao)";

		TypedQuery<Atividade> query = entityManager.createQuery(jpql, Atividade.class);
		query.setParameter("pFracao", fracoes);

		List<Atividade> atividades = query.getResultList();

		for (Atividade atividade : atividades) {
			System.out.println(atividade.getAssuntoAtividade().getNomeAtividade());
		}

	}

	@Test
	public void ExecutarPorId() {
		Fracao fracao = new Fracao();
		fracao = entityManager.find(Fracao.class, 80L);
		System.out.println(fracao.getNomeFracao());

		String jpql = "select a from Atividade a JOIN a.assuntoAtividade f where f.fracao.id IN (:pFracao)";

		TypedQuery<Atividade> query = entityManager.createQuery(jpql, Atividade.class);
		query.setParameter("pFracao", fracao.getId());

		List<Atividade> atividades = query.getResultList();

		for (Atividade atividade : atividades) {
			System.out.println(atividade.getAssuntoAtividade().getNomeAtividade());
		}
	}
}
