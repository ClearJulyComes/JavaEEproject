import javax.persistence.*;

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

    public void dbMethod() {
        logger.info("DBMethod started now!");

        UserInfo user = new UserInfo();
        user.setUserLogin(loginAuth);
        user.setUserPassword(password);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ArrayList<UserInfo> people = new ArrayList<UserInfo>();

        try {
            Query query = entityManager.createQuery("SELECT l FROM UserInfo l WHERE l.userLogin = :userLoginParam");
            query.setParameter("userLoginParam", user.getUserLogin());
            UserInfo userLoginDB = (UserInfo) query.getSingleResult();
            logger.info("From DB login: "+userLoginDB.getUserLogin()+" password: " + userLoginDB.getUserPassword());
            if(!userLoginDB.getUserPassword().equals(user.getUserPassword())){
                this.loginAuth = null;
                logger.info("Make null");
            }else{
                Query queryfriends = entityManager.createQuery("SELECT l FROM UserInfo l WHERE l.userLogin = :userLoginParam");
                queryfriends.setParameter("userLoginParam", user.getUserLogin());
            }
            logger.info("So what: " + loginAuth);
        }catch (Exception e){
            logger.warn("Error with DB query: " + e);
        }
    }

    public void dbMethodCookie(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserInfo userInfo = entityManager.find(UserInfo.class, this.loginAuth);
    }

    public String getLoginAuth() {
        return loginAuth;
    }
}
