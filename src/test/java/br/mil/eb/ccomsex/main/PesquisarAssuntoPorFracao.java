package br.mil.eb.ccomsex.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		usuario = manager.find(Usuario.class, 8L);

		List<Fracao> fracoes = new ArrayList<>();
		fracoes = usuario.getFracoes();

		String jpql = "select a from AssuntoAtividade a JOIN a.fracao f where f IN (:pFracao)";

		TypedQuery<AssuntoAtividade> query = manager.createQuery(jpql, AssuntoAtividade.class);
		query.setParameter("pFracao", fracoes);

		List<AssuntoAtividade> assuntos = query.getResultList();

		for (AssuntoAtividade assunto : assuntos) {
			System.out.println(assunto.getNomeAtividade());
		}

	}
}
