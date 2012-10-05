package ru.n5g.birthdays.core.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author belyaev
 */
public class EventTypeDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String IS_SINGLE = "isSingle";

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

  public Boolean getSingle() {
    return get(IS_SINGLE);
  }

  public void setSingle(Boolean single) {
    set(IS_SINGLE, single);
  }
}
