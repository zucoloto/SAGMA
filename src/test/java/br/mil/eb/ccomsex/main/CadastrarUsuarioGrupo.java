package br.mil.eb.ccomsex.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.mil.eb.ccomsex.atv.model.entity.Grupo;
import br.mil.eb.ccomsex.atv.model.entity.Usuario;
import br.mil.eb.ccomsex.util.jpa.JPAUtil;

public class CadastrarUsuarioGrupo {

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
		usuario = manager.find(Usuario.class, 3L);
		System.out.println(usuario.getNomeUsuario());

		Grupo grupo = new Grupo();
		grupo = manager.find(Grupo.class, 3L);
		System.out.println(grupo.getNomeGrupo());

		usuario.getGrupos().add(grupo);

		manager.merge(usuario);
	}
}
