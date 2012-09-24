package ru.n5g.birthdays.core.server.bean;

import javax.persistence.*;

@Entity
@Table(name = "people")
public class Contact {
  private Long id;
  private String firstName;
  private String lastName;
  private String middleName;
  private String nickname;
  private User user;
  private String comment;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id ", nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "first_name", nullable = false, length = 255)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name", nullable = false, length = 255)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "middle_name", nullable = true, length = 255)
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  @Column(name = "comment", length = 1000)
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Column(name="nickname", length = 255)
  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
