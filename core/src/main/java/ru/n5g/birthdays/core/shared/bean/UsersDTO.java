package ru.n5g.birthdays.core.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class UsersDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String LOGIN = "login";
  public static final String PASSWORD = "password";
  public static final String COMMENT = "comment";
  public static final String ROLE = "role";

  public Long getId() {
    return get(ID);
  }

  public void setId(Long id) {
    set(ID, id);
  }

  public String getLogin() {
    return get(LOGIN);
  }

  public void setLogin(String login) {
    set(LOGIN, login);
  }

  public String getPassword() {
    return get(PASSWORD);
  }

  public void setPassword(String password) {
    set(PASSWORD, password);
  }

  public String getComment() {
    return get(COMMENT);
  }

  public void setComment(String comment) {
    set(COMMENT, comment);
  }

  public UserRoleDTO getRole() {
    return get(ROLE);
  }

  public void setRole(UserRoleDTO role) {
    set(ROLE, role);
  }
}
