package ru.n5g.birthdays.note_book.contact.shared.bean;

import java.util.List;

import ru.n5g.birthdays.core.shared.bean.ContactDTO;

/**
 * @author home
 */
public class ContactEditDTO extends ContactDTO {
  public static final String EVENT_LIST = "eventList";

  public List<EventListDTO> getEventList() {
    return get(EVENT_LIST);
  }

  public void setEventList(List<EventListDTO> eventList) {
    set(EVENT_LIST, eventList);
  }
}
