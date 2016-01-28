package br.mil.eb.ccomsex.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BDCCOMSEXPU");

	public static EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

	public static Statistics getStatistics(EntityManager manager) {
		return ((Session) manager.getDelegate()).getSessionFactory().getStatistics();
	}

}
