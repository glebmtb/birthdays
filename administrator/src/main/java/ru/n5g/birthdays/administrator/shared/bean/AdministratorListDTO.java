package ru.n5g.birthdays.administrator.shared.bean;

import ru.n5g.birthdays.core.shared.bean.UserDTO;

/**
 * @author belyaev
 */
public class AdministratorListDTO extends UserDTO {
  public static final String COUNT_CONTACT = "countContact";

  public Integer getCountContact() {
    return get(COUNT_CONTACT);
  }

  public void setCountContact(Integer countContact) {
    set(COUNT_CONTACT, countContact);
  }
}
