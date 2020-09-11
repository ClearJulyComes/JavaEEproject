package DBMethods;

import Entities.Friends;
import Entities.Login;
import org.apache.logging.log4j.LogManager;

import javax.persistence.*;

public class DeleteFriend {
    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddFriend.class);
    private String userLogin;
    private String hisFriend;

    public DeleteFriend(String userLogin, String hisFriend){
        this.hisFriend = hisFriend;
        this.userLogin = userLogin;
    }

    public void deleteUserFriend(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        logger.info("Delete Method works");
        Login userInfo = entityManager.find(Login.class, userLogin);
        Login hisFriendInfo = entityManager.find(Login.class, hisFriend);
        Query query = entityManager.createQuery("SELECT f FROM Friends f WHERE " +
                "f.userLogin = :userLogin AND f.hisFriend = :hisFriend");
        query.setParameter("userLogin", userInfo);
        query.setParameter("hisFriend", hisFriendInfo);
        Friends friendship = (Friends) query.getSingleResult();
        entityManager.getTransaction().begin();
        entityManager.remove(friendship);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
