package br.mil.eb.ccomsex.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.StatusUsuario;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class CadastrarUsuario {

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
		Usuario usuario = new Usuario();
		usuario.setIdentidade("9");
		usuario.setNomeUsuario("usuario");
		usuario.setEmail("usuario@ccomsex.eb.mil.br");
		usuario.setStatusUsuario(StatusUsuario.ATIVO);
		usuario.setSenha("123");

		manager.persist(usuario);
	}
}
