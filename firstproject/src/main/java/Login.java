import models.LogininfoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Login {
    private static final String PERSISTENT_UNIT_NAME = "firstprojectlogin";

    public void addNewProfile (String login, String password){
        LogininfoEntity logininfoEntity = new LogininfoEntity();
        logininfoEntity.setLogin(login);
        logininfoEntity.setPassword(password);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(logininfoEntity);
        et.commit();
        em.close();
        emf.close();
    }
}
