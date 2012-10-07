package ru.n5g.birthdays.note_book.contact.shared.bean;

import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;

/**
 * @author home
 */
public class RpcWhiteListContact extends RpcWhiteList {
  public ContactEditDTO contactEditDTO;
  public EventListDTO eventListDTO;

  public RpcWhiteListContact() {
    super();
    contactEditDTO = new ContactEditDTO();
    eventListDTO = new EventListDTO();
  }
}
