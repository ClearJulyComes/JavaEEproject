package Entities;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"userLogin", "hisFriend"})})
public class Friends {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long friendshipId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userLogin", nullable = false)
  private Login userLogin;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "hisFriend", nullable = false)
  private Login hisFriend;

  public Friends(){

  }

  public Friends(Login userLogin, Login hisFriend) {
    this.userLogin = userLogin;
    this.hisFriend = hisFriend;
  }

  public long getFriendshipId() {
    return friendshipId;
  }

  public Login getUserLogin() {
    return userLogin;
  }

  public Login getHisFriend() {
    return hisFriend;
  }

  public void setFriendshipId(long friendshipId) {
    this.friendshipId = friendshipId;
  }

  public void setUserLogin(Login userLogin) {
    this.userLogin = userLogin;
  }

  public void setHisFriend(Login hisFriend) {
    this.hisFriend = hisFriend;
  }
}
