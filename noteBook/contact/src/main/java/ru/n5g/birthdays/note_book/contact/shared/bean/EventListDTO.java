package ru.n5g.birthdays.note_book.contact.shared.bean;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModelData;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author home
 */
public class EventListDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String DATE_EVENT = "dateEvent";
  public static final String YEAR = "year";
  public static final String EVENT_TYPE = "eventType";

  public Long getId() {
    return get(ID);
  }

  public void setId(Long id) {
    set(ID, id);
  }

  public Date getDateEvent() {
    return get(DATE_EVENT);
  }

  public void setDateEvent(Date dateEvent) {
    set(DATE_EVENT, dateEvent);
  }

  public Number getYear() {
    return get(YEAR);
  }

  public void setYear(Number year) {
    set(YEAR, year);
  }

  public EventTypeDTO getEventType() {
    return get(EVENT_TYPE);
  }

  public void setEventType(EventTypeDTO eventType) {
    set(EVENT_TYPE, eventType);
  }
}
