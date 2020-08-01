import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class UserInfo {

    @Id
    private String userLogin;
    @NotNull
    private String userPassword;

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
