package DBMethods;

import Entities.Friends;
import Entities.Login;
import org.apache.logging.log4j.LogManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Класс для работы с БД и добавлением в друзья обоих пользователей.
 */
public class AddFriend {

    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AddFriend.class);
    private String newFriend;
    private String userLogin;

    public AddFriend(String newFriend, String userLogin){
        this.newFriend = newFriend;
        this.userLogin = userLogin;
    }

    public void addMethod(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Login userInfo = entityManager.find(Login.class, userLogin);
        Login hisFriend = entityManager.find(Login.class, newFriend);
        Friends friendship = new Friends(userInfo, hisFriend);
        Friends friendshipSecond = new Friends(hisFriend, userInfo);
        entityManager.getTransaction().begin();
        entityManager.persist(friendship);
        entityManager.persist(friendshipSecond);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
