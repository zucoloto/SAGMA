package br.mil.eb.ccomsex.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.stat.Statistics;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class CadastrarTesteStatistics {
	private EntityManager manager;
	private EntityTransaction trx;
	private Statistics statistics;

	@Before
	public void setUp() {
		manager = JPAUtil.createEntityManager();
		statistics = JPAUtil.getStatistics(manager);
		trx = manager.getTransaction();
		trx.begin();
	}

	@After
	public void tearDown() {
		trx.rollback();
		// trx.commit();
		manager.close();
	}

	@Test
	public void deveInserirUmObjetoSimples() {
		Grupo grupo = new Grupo();
		grupo.setNomeGrupo("Teste1");
		grupo.setDescricao("Usuário Teste do Sistema");

		manager.persist(grupo);
		
		Assert.assertEquals(1, statistics.getEntityInsertCount());
	}
}
