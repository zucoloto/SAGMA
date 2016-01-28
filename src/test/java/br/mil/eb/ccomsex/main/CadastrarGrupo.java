package br.mil.eb.ccomsex.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class CadastrarGrupo {
	private EntityManager manager;
	private EntityTransaction trx;

	@Before
	public void setUp() {
		manager = JPAUtil.createEntityManager();
		trx = manager.getTransaction();
		trx.begin();
	}

	@After
	public void tearDown() {
		trx.commit();
		manager.close();
	}

	@Test
	public void Executar() {
		Grupo grupo = new Grupo();
		grupo.setNomeGrupo("Teste");
		grupo.setDescricao("Usuário Teste do Sistema");

		manager.persist(grupo);
	}
}
