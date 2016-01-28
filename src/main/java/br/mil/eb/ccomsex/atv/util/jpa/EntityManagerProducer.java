package br.mil.eb.ccomsex.atv.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory emf;

	public EntityManagerProducer() {
		emf = Persistence.createEntityManagerFactory("BDCCOMSEXPU");
	}

	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager em) {
		em.close();
	}

}
