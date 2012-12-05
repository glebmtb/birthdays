package ru.n5g.birthdays.note_book.contact.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author home
 */
public class EventListDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String DAY = "day";
  public static final String MONTH = "month";
  public static final String YEAR = "year";
  public static final String EVENT_TYPE = "eventType";
  public static final String IS_DELETE = "isDelete";

  public Long getId() {
    return get(ID);
  }

  public void setId(Long id) {
    set(ID, id);
  }

  public Integer getDay() {
    return get(DAY);
  }

  public void setDay(Integer day) {
    set(DAY, day);
  }

  public Integer getMonth() {
    return get(MONTH);
  }

  public void setMonth(Integer month) {
    set(MONTH, month);
  }

  public Integer getYear() {
    return get(YEAR);
  }

  public void setYear(Integer year) {
    set(YEAR, year);
  }

  public EventTypeDTO getEventType() {
    return get(EVENT_TYPE);
  }

  public void setEventType(EventTypeDTO eventType) {
    set(EVENT_TYPE, eventType);
  }

  public Boolean getDelete() {
    return get(IS_DELETE);
  }

  public void setDelete(Boolean delete) {
    set(IS_DELETE, delete);
  }
}
