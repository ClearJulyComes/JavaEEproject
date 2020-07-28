import javax.persistence.*;
import java.util.logging.Logger;

public class Login implements DBInterface {
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private String login;
    private String password;

    //@PersistenceContext(unitName = PERSISTENT_UNIT_NAME)
    //EntityManager entityManager;

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public void dbMethod() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUserLogin(login);
        userLogin.setUserPassword(password);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(userLogin);
        entityManager.getTransaction().commit();
        entityManager.close();
        emf.close();
    }
}
