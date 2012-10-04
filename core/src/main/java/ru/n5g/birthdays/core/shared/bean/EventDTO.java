package ru.n5g.birthdays.core.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author belyaev
 */
public class EventDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String EVENT_TYPE = "eventType";

  public Long getId() {
    return get(ID);
  }

  public void setId(Long id) {
    set(ID, id);
  }

  public EventTypeDTO getEventType() {
    return get(EVENT_TYPE);
  }

  public void setEventType(EventTypeDTO eventType) {
    set(EVENT_TYPE, eventType);
  }
}
