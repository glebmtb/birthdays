package ru.n5g.birthdays.core.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author belyaev
 */
public class ContactDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String FIRST_NAME = "firstName";
  public static final String LAST_NAME = "lastName";
  public static final String MIDDLE_NAME = "middleName";
  public static final String NICKNAME = "nickname";
  public static final String USER_ID = "userId";
  public static final String COMMENT = "comment";

  public Long getId() {
    return get(ID);
  }

  public void setId(Long id) {
    set(ID, id);
  }

  public String getFirstName() {
    return get(FIRST_NAME);
  }

  public void setFirstName(String firstName) {
    set(FIRST_NAME, firstName);
  }

  public String getLastName() {
    return get(LAST_NAME);
  }

  public void setLastName(String lastName) {
    set(LAST_NAME, lastName);
  }

  public String getMiddleName() {
    return get(MIDDLE_NAME);
  }

  public void setMiddleName(String middleName) {
    set(MIDDLE_NAME, middleName);
  }

  public String getNickname() {
    return get(NICKNAME);
  }

  public void setNickname(String nickname) {
    set(NICKNAME, nickname);
  }

  public Long getUserId() {
    return get(USER_ID);
  }

  public void setUserId(Long userId) {
    set(USER_ID, userId);
  }

  public String getComment() {
    return get(COMMENT);
  }

  public void setComment(String comment) {
    set(COMMENT, comment);
  }
}
