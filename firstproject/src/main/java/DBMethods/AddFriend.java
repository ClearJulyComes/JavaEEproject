package DBMethods;

import Controllers.FriendController;
import Entities.Friends;
import Entities.UserInfo;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddFriend {

    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddFriend.class);
    private String newFriend;
    private  String userLogin;

    public AddFriend(String newFriend, String userLogin){
        this.newFriend = newFriend;
        this.userLogin = userLogin;
    }

    public void addMethod(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        logger.info("addMethod works");
        UserInfo userInfo = entityManager.find(UserInfo.class, userLogin);
        UserInfo hisFriend = entityManager.find(UserInfo.class, newFriend);
        logger.info("everything ok now");
        Friends friendship = new Friends(userInfo, hisFriend);
        entityManager.getTransaction().begin();
        entityManager.persist(friendship);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
