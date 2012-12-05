package ru.n5g.birthdays.core.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class UserDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String LOGIN = "login";
  public static final String PASSWORD = "password";
  public static final String COMMENT = "comment";
  public static final String ROLE = "role";
  public static final String COUNT_CONTACT = "countContact";
  public static final String USER_NAME = "userName";

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

  public Integer getCountContact() {
    return get(COUNT_CONTACT);
  }

  public void setCountContact(Integer countContact) {
    set(COUNT_CONTACT, countContact);
  }

  public String getUserName() {
    return get(USER_NAME);
  }

  public void setUserName(String userName) {
    set(USER_NAME, userName);
  }
}
