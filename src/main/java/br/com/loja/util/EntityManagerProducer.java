package br.com.loja.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer implements Serializable  {
	
    private static final long serialVersionUID = 1L;
    
	private String persistence_unit_name = "unit_app";

	@Produces @ApplicationScoped
	public EntityManagerFactory criaFactory() {
		return Persistence.createEntityManagerFactory(persistence_unit_name);
	}

	@RequestScoped
    @Produces
    @Default
	public EntityManager getEntityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}
	
	public void  destroy(@Disposes EntityManager em){
		if (em.isOpen()) {
			em.close();
        }
	}

}
