package ru.n5g.birthdays.core.server.bean;


import javax.persistence.*;

import ru.n5g.birthdays.core.shared.bean.UsersDTO;

@Entity
@Table(name = "users")
public class Users {
  private Long id;
  private String login;
  private String password;
  private String comment;
  private UserRole role;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id ", nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "login", nullable = false, length = 255)
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Column(name = "password", nullable = false, length = 255)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "comment", length = 255)
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @ManyToOne
  @JoinColumn(name = "user_role_id", nullable = false)
  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public static UsersDTO convert(Users bean) {
    UsersDTO dto = new UsersDTO();
    dto.setId(bean.getId());
    dto.setLogin(bean.getLogin());
     dto.setRole(UserRole.convert(bean.getRole()));
    dto.setComment(bean.getComment());
    return dto;
  }

  public static Users convert(UsersDTO dto) {
    Users bean = new Users();
    bean.setId(dto.getId());
    bean.setLogin(dto.getLogin());
    bean.setRole(UserRole.convert(dto.getRole()));
    bean.setPassword(dto.getPassword());
    bean.setComment(dto.getComment());
    return bean;
  }
}
