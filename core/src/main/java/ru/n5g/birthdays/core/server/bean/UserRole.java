package ru.n5g.birthdays.core.server.bean;

import javax.persistence.*;

import ru.n5g.birthdays.core.shared.bean.UserRoleCode;

/**
 * @author home
 */
@Entity
@Table(name = "user_role")
public class UserRole {
  private Long id;
  private String name;
  private UserRoleCode code;
  private String comment;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_role_id ", nullable = false, unique = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name", nullable = false, length = 255)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "code", nullable = false, length = 255)
  public UserRoleCode getCode() {
    return code;
  }

  public void setCode(UserRoleCode code) {
    this.code = code;
  }

  @Column(name = "comment", length = 1000)
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
