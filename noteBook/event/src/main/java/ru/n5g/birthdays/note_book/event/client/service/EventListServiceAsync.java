package ru.n5g.birthdays.note_book.event.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.EventDTO;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;

public interface EventListServiceAsync {
  void loadList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<EventDTO>> callback);

  void registerClasses(RpcWhiteList whiteList, AsyncCallback<RpcWhiteList> async);
}
