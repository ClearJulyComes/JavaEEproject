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
    private String user_id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userInfoLogin", nullable = false)
    private Login userInfoLogin;

    @NotNull
    private String userPassword;

    public UserInfo(){
    }

    public UserInfo(Login userInfoLogin, String userPassword){
        this.user_id = userInfoLogin.getUserLogin();
        this.userInfoLogin = userInfoLogin;
        this.userPassword = userPassword;
    }

    public Login getUserInfoLogin() {
        return userInfoLogin;
    }

    public void setUserInfoLogin(Login userInfoLogin) {
        this.userInfoLogin = userInfoLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }

}
