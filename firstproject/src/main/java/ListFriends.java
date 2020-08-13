import javax.persistence.*;

@Entity
public class ListFriends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfo userLoginF;

    private String hisFriend;

    public UserInfo getUserLoginF() {
        return userLoginF;
    }

    public void setUserLoginF(UserInfo userLoginF) {
        this.userLoginF = userLoginF;
    }

    public String getHisFriend() {
        return hisFriend;
    }

    public void setHisFriend(String hisFriend) {
        this.hisFriend = hisFriend;
    }
}