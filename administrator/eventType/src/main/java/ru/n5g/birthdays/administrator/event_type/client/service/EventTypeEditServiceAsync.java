package ru.n5g.birthdays.administrator.event_type.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author belyaev
 */
public interface EventTypeEditServiceAsync {
  void saveContact(EventTypeDTO dto, AsyncCallback<Void> callback);
}
