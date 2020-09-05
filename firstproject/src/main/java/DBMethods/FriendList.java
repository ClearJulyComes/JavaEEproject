package DBMethods;

import Entities.Friends;
import Entities.UserInfo;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class FriendList {
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddFriend.class);
    private String userLogin;
    public FriendList(String userLogin){
        this.userLogin = userLogin;
    };

    public List<Friends> getFriendList(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        logger.info("addMethod works");
        Query query = entityManager.createQuery("SELECT f FROM Friends f WHERE f.userLogin = :userLoginParam");
        query.setParameter("userLoginParam", this.userLogin);

        return null;
    }
}
