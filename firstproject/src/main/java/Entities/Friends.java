package Entities;

import javax.persistence.*;

@Entity
public class Friends {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long friendshipId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userLogin", nullable = false)
  private UserInfo userLogin;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "hisFriend", nullable = false)
  private UserInfo hisFriend;

  public Friends(){

  }

  public Friends(UserInfo userLogin, UserInfo hisFriend) {
    this.userLogin = userLogin;
    this.hisFriend = hisFriend;
  }

  public long getFriendshipId() {
    return friendshipId;
  }

  public UserInfo getUserLogin() {
    return userLogin;
  }

  public UserInfo getHisFriend() {
    return hisFriend;
  }
}
