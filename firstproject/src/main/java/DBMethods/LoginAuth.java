package DBMethods;

import javax.persistence.*;

import Entities.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

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
        logger.info("DBMethod started now!");

        UserInfo user = new UserInfo();
        user.setUserLogin(loginAuth);
        user.setUserPassword(password);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        UserInfo userInfo = entityManager.find(UserInfo.class, loginAuth);
        logger.info(userInfo.getUserPassword() + " Check user password from db");
        logger.warn(user.getUserPassword().equals(userInfo.getUserPassword()) + " LOOOOOOOOOOOOOOOOOOOOOOOCK");
        return user.getUserPassword().equals(userInfo.getUserPassword());
        /*try {
            Query query = entityManager.createQuery("SELECT l FROM UserInfo l WHERE l.userLogin = :userLoginParam");
            query.setParameter("userLoginParam", user.getUserLogin());
            UserInfo userLoginDB = (UserInfo) query.getSingleResult();
            logger.info("From DB login: "+userLoginDB.getUserLogin()+" password: " + userLoginDB.getUserPassword());
            if(!userLoginDB.getUserPassword().equals(user.getUserPassword())){
                this.loginAuth = null;
                logger.info("Make null");
            }else{
                Query queryFriends = entityManager.createQuery("SELECT l FROM UserInfo l WHERE l.userLogin = :userLoginParam");
                queryFriends.setParameter("userLoginParam", user.getUserLogin());
            }
            logger.info("So what: " + loginAuth);
        }catch (Exception e){
            logger.warn("Error with DB query: " + e);
        } */
    }

    public String getLoginAuth() {
        return loginAuth;
    }
}
