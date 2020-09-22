package DBMethods;

import Entities.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class CheckUser {
    private static final Logger logger = LogManager.getLogger(LoginAuth.class);
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private String loginAuth;

    public CheckUser(String loginAuth){
        this.loginAuth = loginAuth;
    }

    public boolean checkUserMethod() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserInfo userInfo = null;
        userInfo = entityManager.find(UserInfo.class, loginAuth);
        return userInfo != null;
    }

    public String getLoginAuth() {
        return loginAuth;
    }
}
