package ru.n5g.birthdays.core.server.bean;


import javax.persistence.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.n5g.birthdays.core.shared.bean.UserDTO;

@Entity
@Table(name = "\"user\"")
public class User {
  private Long id;
  private String login;
  private String password;
  private String comment;
  private UserRole role;
  private ContactCount contactCount;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id ", nullable = false, unique = true)
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

  public static UserDTO convert(User bean) {
    UserDTO dto = new UserDTO();
    dto.setId(bean.getId());
    dto.setLogin(bean.getLogin());
    dto.setRole(UserRole.convert(bean.getRole()));
    dto.setComment(bean.getComment());
    return dto;
  }

  public static User convert(UserDTO dto) {
    User bean = new User();
    bean.setId(dto.getId());
    bean.setLogin(dto.getLogin());
    bean.setRole(UserRole.convert(dto.getRole()));
    bean.setPassword(dto.getPassword());
    bean.setComment(dto.getComment());
    return bean;
  }

  public static Long getAuthenticationUserId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    AppUserDetails appUserDetails = (AppUserDetails) auth.getPrincipal();
    User user = appUserDetails.getUser();
    return user.getId();
  }

  @OneToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  public ContactCount getContactCount() {
    return contactCount;
  }

  public void setContactCount(ContactCount contactCount) {
    this.contactCount = contactCount;
  }
}
