import models.LogininfoEntity;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class Login implements DBInterface {
    private static final String PERSISTENT_UNIT_NAME = "firstprojectlogin";
    private String login;
    private String password;

    @PersistenceContext(unitName = PERSISTENT_UNIT_NAME)
    EntityManager entityManager;

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void dbMethod() {
        LogininfoEntity logininfoEntity = new LogininfoEntity();
        logininfoEntity.setLogin(login);
        logininfoEntity.setPassword(password);

        //em.getTransaction().begin();
        entityManager.persist(logininfoEntity);
        //em.getTransaction().commit();
    }
}
