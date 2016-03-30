package br.mil.eb.ccomsex.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class PesquisarAssuntoPorFracao {

	private EntityManager manager;

	@Before
	public void setUp() {
		manager = JPAUtil.createEntityManager();
	}

	@After
	public void tearDown() {
		manager.close();
	}

	@Test
	public void Executar() {
		Usuario usuario = new Usuario();
		usuario = manager.find(Usuario.class, 12L);

		//List<Fracao> fracoes = new ArrayList<>();
		//fracoes = usuario.getFracoes();

		Fracao fracao = usuario.getFracoes().get(0);
		System.out.println("Seção: " + fracao.getFracaoPaiId().getNomeFracao());

		String jpql = "select a from AssuntoAtividade a JOIN a.fracao f where f IN (:pFracao)";

		TypedQuery<AssuntoAtividade> query = manager.createQuery(jpql, AssuntoAtividade.class);
		query.setParameter("pFracao", fracao);

		List<AssuntoAtividade> assuntos = query.getResultList();

		for (AssuntoAtividade assunto : assuntos) {
			System.out.println(assunto.getNomeAtividade());
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void listarAssuntoAtividadePorFracao() {
		Usuario usuario = new Usuario();
		usuario = manager.find(Usuario.class, 4L);

		Fracao fracao = new Fracao();
		fracao = usuario.getFracoes().get(0);

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AssuntoAtividade.class);

		criteria.createAlias("fracao", "f");

		criteria.add(Restrictions.or(Restrictions.eq("f.id", fracao.getFracaoPaiId().getId()),
				Restrictions.eq("f.id", fracao.getId())));

		List<AssuntoAtividade> assuntoAtividades = criteria.list();

		for (AssuntoAtividade a : assuntoAtividades) {
			System.out.println(a.getId() + " - " + a.getNomeAtividade() + " - " + a.getFracao().getNomeFracao());
		}
	}
}
