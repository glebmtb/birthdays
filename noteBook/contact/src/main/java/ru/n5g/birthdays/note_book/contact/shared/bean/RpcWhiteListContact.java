package ru.n5g.birthdays.note_book.contact.shared.bean;

import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;

/**
 * @author home
 */
public class RpcWhiteListContact extends RpcWhiteList {
  public ContactEditDTO contactEditDTO;
  public EventListDTO eventListDTO;
  public ContactListDTO contactListDTO;

  public RpcWhiteListContact() {
    this.contactEditDTO = new ContactEditDTO();
    this.eventListDTO = new EventListDTO();
    this.contactListDTO = new ContactListDTO();
  }
}
