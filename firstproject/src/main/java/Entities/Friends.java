package Entities;

import javax.persistence.*;

@Entity
public class Friends {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long friendshipId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userLogin", nullable = false)
  private UserInfo userInfo;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "hisFriend", nullable = false)
  private UserInfo hisFriend;

  public Friends(){

  }

  public Friends(UserInfo userInfo, UserInfo hisFriend) {
    this.userInfo = userInfo;
    this.hisFriend = hisFriend;
  }

  public long getFriendshipId() {
    return friendshipId;
  }

}
