package Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность пользователя
 */
@Entity
public class Login {
    @Id
    private String userLogin;

    public Login(){
    }

    public Login(String userLogin){
        this.userLogin = userLogin;
    }

    @OneToMany(mappedBy = "userLogin", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Friends> userInfo = new HashSet<Friends>();
    @OneToMany(mappedBy = "hisFriend", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Friends> hisFriends = new HashSet<Friends>();
    @OneToOne(mappedBy = "userInfoLogin", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private UserInfo userInfoLogin;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
