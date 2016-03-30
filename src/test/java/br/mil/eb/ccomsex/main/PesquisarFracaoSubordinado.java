package br.mil.eb.ccomsex.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.Fracao;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class PesquisarFracaoSubordinado {

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

		TypedQuery<Fracao> query = null;

		/* listarFracaoPai */
		String jpqlP = "select f from Fracao f where f IN (:pFracoesP) order by ordemQC";
		query = manager.createQuery(jpqlP, Fracao.class);
		query.setParameter("pFracoesP", usuario.getFracoes());
		List<Fracao> fracaoP = query.getResultList();

		for (Fracao f : fracaoP) {
			System.out.println(f.getNomeFracao());
		}

		System.out.println("-----------------------------------------------------");

		/* listarFracaoFilho */
		// String jpqlF = "select f from Fracao f JOIN f.fracaoPaiId p where p
		// IN (select f from Fracao f where f IN (:pFracoesF))";

		String jpqlF = "select f from Fracao f JOIN f.fracaoPaiId p where p IN (:pFracoesF) order by f.ordemQC";
		// String jpql = "select f from Fracao f where f IN (:pFracoes)";
		query = manager.createQuery(jpqlF, Fracao.class);
		query.setParameter("pFracoesF", fracaoP);

		List<Fracao> fracaoF = query.getResultList();

		for (Fracao f : fracaoF) {
			System.out.println(f.getNomeFracao());
		}

		System.out.println("-----------------------------------------------------");

	}
}
