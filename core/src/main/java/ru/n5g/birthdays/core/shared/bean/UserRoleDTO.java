package ru.n5g.birthdays.core.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author belyaev
 */
public class UserRoleDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String CODE = "code";
  public static final String COMMENT = "comment";

  public Long getId() {
    return get(ID);
  }

  public void setId(Long id) {
    set(ID, id);
  }

  public String getName() {
    return get(NAME);
  }

  public void setName(String name) {
    set(NAME, name);
  }

  public UserRoleCode getCode() {
    return get(CODE);
  }

  public void setCode(UserRoleCode code) {
    set(CODE, code);
  }

  public String getComment() {
    return get(COMMENT);
  }

  public void setComment(String comment) {
    set(COMMENT, comment);
  }
}
