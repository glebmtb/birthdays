package ru.n5g.birthdays.administrator.event_type.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author belyaev
 */
@RemoteServiceRelativePath("eventTypeEditService.rpc")
public interface EventTypeEditService extends RemoteService {
  void saveContact(EventTypeDTO dto);
}
