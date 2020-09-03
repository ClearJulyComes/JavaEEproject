package DBMethods;

import Entities.UserInfo;

import javax.persistence.*;

public class LoginReg {
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private String loginReg;
    private String password;

    public LoginReg(String loginReg, String password) {
        this.loginReg = loginReg;
        this.password = password;
    }

    public void dbMethod() {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserLogin(loginReg);
            userInfo.setUserPassword(password);
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(userInfo);
            entityManager.getTransaction().commit();
            entityManager.close();
    }

    public String getLoginReg() {
        return loginReg;
    }

    public String getPassword() {
        return password;
    }
}
