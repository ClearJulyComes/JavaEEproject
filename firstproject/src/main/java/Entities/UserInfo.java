package Entities;

import com.sun.istack.NotNull;
import Entities.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserInfo {

    @Id
    private String userLogin;
    @NotNull
    private String userPassword;
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Friends> friends = new HashSet<Friends>();
    @OneToMany(mappedBy = "hisFriend", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Friends> hisFriends = new HashSet<Friends>();

    public UserInfo(){
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String login) {
        this.userLogin = login;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

}
