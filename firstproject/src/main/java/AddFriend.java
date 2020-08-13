import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AddFriend {

    private static final String PERSISTENT_UNIT_NAME = "UnitName";
    private String newFriend;

    public AddFriend(String newFriend){
        this.newFriend = newFriend;
    }

    public void addMethod(){
        ListFriends listFriends = new ListFriends();
        listFriends.setUserLoginF();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

    }
}
