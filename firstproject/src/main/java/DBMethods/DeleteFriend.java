package DBMethods;

import org.apache.logging.log4j.LogManager;
import javax.persistence.*;

/**
 * Класс для удаления пользователей из друзей у друг друга.
 */
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
        Query query = entityManager.createQuery("DELETE FROM Friends f WHERE f.userLogin.userLogin" +
                " IN (:userLogin, :hisFriend) AND f.hisFriend.userLogin IN (:hisFriend, :userLogin)");
        query.setParameter("userLogin", userLogin);
        query.setParameter("hisFriend", hisFriend);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
