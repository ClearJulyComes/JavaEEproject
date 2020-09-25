package DBMethods;

import javax.persistence.*;
import Entities.Login;
import Entities.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для авторизации через БД.
 */
public class LoginAuth {
    private static final Logger logger = LogManager.getLogger(LoginAuth.class);
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private String loginAuth;
    private String password;

    public LoginAuth(String loginAuth, String password) {
        this.loginAuth = loginAuth;
        this.password = password;
    }

    public boolean dbMethod() {
        Login login = new Login(loginAuth);
        UserInfo user = new UserInfo();
        user.setUserInfoLogin(login);
        user.setUserPassword(password);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserInfo userInfo = entityManager.find(UserInfo.class, loginAuth);
        return user.getUserPassword().equals(userInfo.getUserPassword());
    }

    public String getLoginAuth() {
        return loginAuth;
    }
}
