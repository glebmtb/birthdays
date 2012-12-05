package ru.n5g.birthdays.note_book.event.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author belyaev
 */
public class EventListDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String EVENT_TYPE_NAME = "eventTypeName";
  public static final String CONTACT_FIO = "contactFio";
  public static final String EVENT_DAYS_LEFT = "eventDaysLeft";
  public static final String EVENT_YEARS = "eventYears";
  public static final String EVENT_DAY = "eventDay";

  public Long getId() {
    return get(ID);
  }

  public void setId(Long id) {
    set(ID, id);
  }

  public String getEventTypeName() {
    return get(EVENT_TYPE_NAME);
  }

  public void setEventTypeName(String eventTypeName) {
    set(EVENT_TYPE_NAME, eventTypeName);
  }

  public String getContactFio() {
    return get(CONTACT_FIO);
  }

  public void setContactFio(String contactFio) {
    set(CONTACT_FIO, contactFio);
  }

  public Integer getEventDaysLeft() {
    return get(EVENT_DAYS_LEFT);
  }

  public void setEventDaysLeft(Integer eventDaysLeft) {
    set(EVENT_DAYS_LEFT, eventDaysLeft);
  }

  public String getEventYears() {
    return get(EVENT_YEARS);
  }

  public void setEventYears(String eventYears) {
    set(EVENT_YEARS, eventYears);
  }

  public String getEvenDay() {
    return get(EVENT_DAY);
  }

  public void setEventDay(String eventDay) {
    set(EVENT_DAY, eventDay);
  }
}
