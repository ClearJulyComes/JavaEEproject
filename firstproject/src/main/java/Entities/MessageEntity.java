package Entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long messageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userLogin", nullable = false)
    private Login userLogin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hisFriend", nullable = false)
    private Login hisFriend;

    @NotNull
    private String msg;

    public MessageEntity(){

    }
    public MessageEntity(Login userLogin, Login hisFriend, String msg){
        this.userLogin = userLogin;
        this.hisFriend = hisFriend;
        this.msg = msg;
    }

    public String getUserLogin() {
        return userLogin.getUserLogin();
    }

    public void setUserLogin(Login userLogin) {
        this.userLogin = userLogin;
    }

    public String getHisFriend() {
        return hisFriend.getUserLogin();
    }

    public void setHisFriend(Login hisFriend) {
        this.hisFriend = hisFriend;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getMessageId() {
        return messageId;
    }
}
