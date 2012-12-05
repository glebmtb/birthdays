package ru.n5g.birthdays.note_book.event.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.note_book.event.shared.bean.EventListDTO;

@RemoteServiceRelativePath("eventTypeListService.rpc")
public interface EventListService extends RemoteService  {

  RpcWhiteList registerClasses(RpcWhiteList whiteList);

  BasePagingLoadResult<EventListDTO> loadList(BasePagingLoadConfig loadConfig);
}
