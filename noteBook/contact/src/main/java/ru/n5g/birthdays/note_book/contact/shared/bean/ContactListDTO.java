package ru.n5g.birthdays.note_book.contact.shared.bean;

import ru.n5g.birthdays.core.shared.bean.ContactDTO;

/**
 * @author home
 */
public class ContactListDTO extends ContactDTO {
  public static final String EVENT_LIST = "eventList";

  public String getEventList() {
    return get(EVENT_LIST);
  }

  public void setEventList(String eventList) {
    set(EVENT_LIST, eventList);
  }
}
