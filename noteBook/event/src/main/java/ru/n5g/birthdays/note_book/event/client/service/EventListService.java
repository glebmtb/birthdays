package ru.n5g.birthdays.note_book.event.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.EventDTO;

@RemoteServiceRelativePath("eventTypeListService.rpc")
public interface EventListService extends RemoteService  {

  BasePagingLoadResult<EventDTO> loadList(BasePagingLoadConfig loadConfig);
}
