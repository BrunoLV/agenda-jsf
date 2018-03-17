package br.com.valhala.agenda.db;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManagerProducer {

    private final static Logger logger = LoggerFactory.getLogger(EntityManagerProducer.class);

    private EntityManager entityManager;

    protected void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Produces
    @Default
    @RequestScoped
    public EntityManager produce() {
        logger.info("Criando ENTITY MANAGER");
        entityManager = Persistence.createEntityManagerFactory("agenda").createEntityManager();
        return entityManager;
    }

}
