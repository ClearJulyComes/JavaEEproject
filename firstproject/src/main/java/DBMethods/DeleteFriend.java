package DBMethods;

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
        logger.info("Delete Method works " + userLogin + " his friend: " + hisFriend);
        //Login userInfo = entityManager.find(Login.class, userLogin);
        //Login hisFriendInfo = entityManager.find(Login.class, hisFriend);
        //Query query = entityManager.createQuery("SELECT f FROM Friends f WHERE " +
        //        "f.userLogin = :userLogin AND f.hisFriend = :hisFriend");
        Query query = entityManager.createQuery("DELETE FROM Friends f WHERE " +
                "f.userLogin.userLogin IN (:userLogin, :hisFriend) AND f.hisFriend.userLogin IN (:hisFriend, :userLogin)");
        query.setParameter("userLogin", userLogin);
        query.setParameter("hisFriend", hisFriend);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
