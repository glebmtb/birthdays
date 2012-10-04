package ru.n5g.birthdays.administrator.event_type.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

public interface EventTypeListServiceAsync {

  void loadContactList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<EventTypeDTO>> callback);

  void deleteContact(List<EventTypeDTO> dtoList, AsyncCallback<Void> callback);
}
