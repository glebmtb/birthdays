package ru.n5g.birthdays.core.shared.bean;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 * @author belyaev
 */
public class EventTypeDTO extends BaseModelData {
  public static final String ID = "id";
  public static final String NAME = "name";

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
}
