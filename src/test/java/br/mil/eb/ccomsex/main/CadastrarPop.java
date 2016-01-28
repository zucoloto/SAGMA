package br.mil.eb.ccomsex.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.AssuntoAtividade;
import br.mil.eb.ccomsex.atv.model.entity.Pop;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class CadastrarPop {

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
		AssuntoAtividade assunto = new AssuntoAtividade();
		assunto = manager.find(AssuntoAtividade.class, 2L);
		System.out.println(assunto.getNomeAtividade());

		if (assunto.getPop() != null) {
			System.out.println("Pop já cadastrado");
		} else {
			Pop pop = new Pop();
			String diretorio = "/home/zucoloto/Imagens/uploaded/";
			pop.setPop(diretorio);
			pop.setAssuntoAtividade(assunto);

			manager.merge(pop);
			System.out.println("Pop cadastrado");
		}
	}
	
}
