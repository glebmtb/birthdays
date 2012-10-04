package ru.n5g.birthdays.administrator.event_type.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

@RemoteServiceRelativePath("eventTypeListService.rpc")
public interface EventTypeListService extends RemoteService  {

  BasePagingLoadResult<EventTypeDTO> loadContactList(BasePagingLoadConfig loadConfig);

  void deleteContact(List<EventTypeDTO> dtoList);
}
