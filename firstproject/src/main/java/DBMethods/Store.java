package DBMethods;

public class Store {
    private String userLogin;
    private String hisFriend = "";
    private String msg = "";
    private long id;

    public Store(String userLogin, long id){
        this.userLogin = userLogin;
        this.id = id;
    }
    public Store(String userLogin, String hisFriend, String msg, Long id){
        this.userLogin = userLogin;
        this.hisFriend = hisFriend;
        this.msg = msg;
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getHisFriend() {
        return hisFriend;
    }

    public String getMsg() {
        return msg;
    }

    public long getId() {
        return id;
    }
}
