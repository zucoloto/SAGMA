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
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class PesquisarFracao {

	private EntityManager manager;

	@Before
	public void setUp() {
		manager = JPAUtil.createEntityManager();
	}

	@After
	public void tearDown() {
		manager.close();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listarFracao() {
		Usuario usuario = new Usuario();
		usuario = manager.find(Usuario.class, 12L);

		System.out.println("Usuário: " + usuario.getNomeUsuario());
		System.out.println("-------------------");
		// Long id = null;

		for (Fracao f : usuario.getFracoes()) {
			// id = f.getFracaoPaiId().getId();
			System.out.println(f.getFracaoPaiId().getId() + f.getFracaoPaiId().getNomeFracao());
		}

		System.out.println("-------------------");

		/*
		 * Session session = this.manager.unwrap(Session.class); Criteria
		 * criteria = session.createCriteria(Usuario.class);
		 * 
		 * criteria.add(Restrictions.eq("id", 4L));
		 * 
		 * usuario = (Usuario) criteria.uniqueResult();
		 * 
		 * System.out.println("Usuário: " + usuario.getNomeUsuario());
		 * 
		 * for (Fracao f : usuario.getFracoes()) { id =
		 * f.getFracaoPaiId().getId();
		 * System.out.println(f.getFracaoPaiId().getId() +
		 * f.getFracaoPaiId().getNomeFracao()); }
		 * 
		 * System.out.println("-------------------");
		 */

		Session session = this.manager.unwrap(Session.class);
		Criteria criteriaAtividade = session.createCriteria(Atividade.class);

		// criteriaAtividade.add(Restrictions.eq("statusAtividade",
		// "INICIADA"));

		// criteriaAtividade.add(Restrictions.isNull("observacao"));

		List<Atividade> atividades = criteriaAtividade.list();

		for (Atividade a : atividades) {
			System.out.println(a.getId());
			System.out.println("-------------------");
		}

		/*
		 * Session session = this.manager.unwrap(Session.class); Criteria
		 * criteria = session.createCriteria(Fracao.class);
		 * 
		 * //criteria.add(Restrictions.eq("id", id));
		 * 
		 * criteria.add(Restrictions.in("Fracao", usuario.getFracoes()));
		 * 
		 * List<Fracao> fracao = criteria.list();
		 * 
		 * for (Fracao f : fracao) { System.out.println(f.getNomeFracao()); }
		 * 
		 * 
		 * System.out.println("-------------------");
		 */

	}

	@Test
	public void listarFracaoPaiPorUsuarioList() {
		Usuario usuario = new Usuario();
		usuario = manager.find(Usuario.class, 12L);

		String jpql = "select f from Fracao f where f IN (:pFracoes) group by f.fracaoPaiId order by ordemQC";
		TypedQuery<Fracao> query = manager.createQuery(jpql, Fracao.class);
		query.setParameter("pFracoes", usuario.getFracoes());
		List<Fracao> fracoesPai = query.getResultList();

		for (Fracao f : fracoesPai) {
			System.out.println(f.getId() + "-" + f.getFracaoPaiId().getId() + "-" + f.getFracaoPaiId().getNomeFracao());

			jpql = "select f from Fracao f where f.fracaoPaiId.id = (:pFracoesF)";

			query = manager.createQuery(jpql, Fracao.class);
			query.setParameter("pFracoesF", f.getFracaoPaiId().getId());

			List<Fracao> fracoesF = query.getResultList();
			for (Fracao ff : fracoesF) {
				System.out.println(ff.getNomeFracao());
			}
		}

		/*
		 * System.out.println(
		 * "-------------------------------------------------"); jpql =
		 * "select f from Fracao f INNER JOIN f.fracaoPaiId p where p IN (select f from Fracao f where f IN (:pFracoes) group by f.fracaoPaiId order by ordemQC)"
		 * ; query = manager.createQuery(jpql, Fracao.class);
		 * query.setParameter("pFracoes", usuario.getFracoes()); List<Fracao>
		 * fracoesF = query.getResultList();
		 * 
		 * System.out.println(fracoesF); for (Fracao f : fracoesF) {
		 * System.out.println(f.getFracaoPaiId().getId() +
		 * f.getFracaoPaiId().getNomeFracao()); }
		 * 
		 * System.out.println(
		 * "-------------------------------------------------");
		 * 
		 * Session session = this.manager.unwrap(Session.class); Criteria
		 * criteria = session.createCriteria(Fracao.class);
		 * criteria.createAlias("fracaoPaiId", "fp");
		 * criteria.add(Restrictions.in("fp.id", fracoesPai));
		 * 
		 * System.out.println(fracoesPai);
		 * 
		 * List<Fracao> fracao = criteria.list(); for (Fracao f : fracao) {
		 * System.out.println("TESTE" + f.getNomeFracao()); }
		 * 
		 * System.out.println(
		 * "-------------------------------------------------"); jpql =
		 * "select f from Fracao f INNER JOIN f.fracaoPaiId p where p IN (:pFracoesPai)"
		 * ; query = manager.createQuery(jpql, Fracao.class);
		 * query.setParameter("pFracoesPai", fracoesPai); List<Fracao> fracoesF
		 * = query.getResultList();
		 * 
		 * System.out.println(fracoesF); for (Fracao f : fracoesF) {
		 * System.out.println(f.getFracaoPaiId().getId() +
		 * f.getFracaoPaiId().getNomeFracao()); }
		 */

	}

	@Test
	public void listarFracaoPorUsuarioId() {
		Fracao fracao = new Fracao();
		fracao = manager.find(Fracao.class, 78L);

		String jpql = "select f from Fracao f JOIN f.fracaoPaiId p where p.id = :pFracoes order by f.ordemQC";
		TypedQuery<Fracao> query = manager.createQuery(jpql, Fracao.class);
		query.setParameter("pFracoes", fracao.getFracaoPaiId().getId());
		List<Fracao> fracoes = query.getResultList();

		for (Fracao f : fracoes) {
			System.out.println(f.getNomeFracao());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listarFracaoPorUsuarioIdHib() {
		Session session = this.manager.unwrap(Session.class);

		// 1º Selecionar Usuário
		Usuario usuario = new Usuario();
		usuario = manager.find(Usuario.class, 4L);

		Criteria criteriaU = session.createCriteria(Fracao.class);
		criteriaU.add(Restrictions.in("subFracoes", usuario.getFracoes()));

		// criteriaU.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Fracao> fracoes = criteriaU.list();

		for (Fracao f : fracoes) {
			System.out.println(f.getOrdemQC() + " - " + f.getNomeFracao());
		}

		/*
		 * Fracao fracao = new Fracao(); fracao = manager.find(Fracao.class,
		 * 79L);
		 * 
		 * 
		 * Criteria criteria = session.createCriteria(Fracao.class); //
		 * criteria.add(Restrictions.eq("id", 78L));
		 * criteria.add(Restrictions.eq("fracaoPaiId.id",
		 * fracao.getFracaoPaiId().getId()));
		 * 
		 * List<Fracao> fracoes = criteria.list();
		 * 
		 * System.out.println(fracao.getFracaoPaiId().getOrdemQC() + " - " +
		 * fracao.getFracaoPaiId().getNomeFracao());
		 * 
		 * for (Fracao f : fracoes) { System.out.println(f.getOrdemQC() + " - "
		 * + f.getNomeFracao()); }
		 */
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listarFracaoPaiUsuario() {
		// 1º Selecionar Usuário
		Usuario usuario = new Usuario();
		usuario = manager.find(Usuario.class, 8L);

		// 2º Frações em que o Usuário pertence
		List<Fracao> fracoes = usuario.getFracoes();

		List<Fracao> resultado = new ArrayList<>();

		for (Fracao f : fracoes) {
			System.out.println("-------------------------------------------------");
			System.out.println(f.getFracaoPaiId().getOrdemQC() + " - " + f.getFracaoPaiId().getNomeFracao());

			// resultado.add(f);

			Session session = this.manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Fracao.class);

			if (f.getNomeFracao().equals("Chefia") || f.getNomeFracao().equals("Subchefia")) {
				criteria.add(Restrictions.eq("fracaoPaiId.id", f.getFracaoPaiId().getId()));
			} else {
				criteria.add(Restrictions.eq("id", f.getId()));
			}

			List<Fracao> ffs = criteria.list();
			for (Fracao ff : ffs) {
				resultado.add(ff);
				System.out.println(ff.getOrdemQC() + " - " + ff.getNomeFracao());
			}
		}

		System.out.println("-------------------------------------------------");
		for (Fracao r : resultado) {
			System.out.println("Resultado: " + r.getOrdemQC() + " - " + r.getNomeFracao());
		}
	}

	// Valendo
	@Test
	public void listarFracaoPaiFilhoPorUsuario() {
		Usuario usuario = new Usuario();
		usuario = manager.find(Usuario.class, 3L);
		listarFracaoPaiPorUsuario(usuario.getFracoes());
	}

	public void listarFracaoPaiPorUsuario(List<Fracao> fracoes) {
		String jpql = "select f from Fracao f where f IN (:pFracoes) group by f.fracaoPaiId order by ordemQC";
		TypedQuery<Fracao> query = manager.createQuery(jpql, Fracao.class);
		query.setParameter("pFracoes", fracoes);
		List<Fracao> fracoesPai = query.getResultList();
		listarFracaoFilhoPorUsuario(fracoesPai);
	}

	public void listarFracaoFilhoPorUsuario(List<Fracao> fracoesPai) {
		List<Fracao> resultado = new ArrayList<>();
		for (Fracao f : fracoesPai) {
			
			//System.out.println(f.getId() + "-" + f.getFracaoPaiId().getId() + "-" + f.getFracaoPaiId().getNomeFracao());
			
			resultado.add(f.getFracaoPaiId());
			
			TypedQuery<Fracao> query = null;
			String jpql = null;
			
//			if (f.getFracaoPaiId().getId() == 1L) {
				jpql = "select f from Fracao f where f.fracaoPaiId.id = (:pFracoesF)";
				query = manager.createQuery(jpql, Fracao.class);
				query.setParameter("pFracoesF", f.getFracaoPaiId().getId());
//			} else {
//				jpql = "select f from Fracao f where f.id = (:pFracoesF)";
//				query = manager.createQuery(jpql, Fracao.class);
//				query.setParameter("pFracoesF", f.getId());
//			}

			List<Fracao> fracoesF = query.getResultList();
			for (Fracao ff : fracoesF) {
				resultado.add(ff);
				//System.out.println(ff.getNomeFracao());
			}
		}
		
		for (Fracao r : resultado) {
			System.out.println(r.getId() + " - " + r.getNomeFracao());
		}
	}
}
