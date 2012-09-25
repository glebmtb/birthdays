package ru.n5g.birthdays.note_book.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;

public interface ContactListServiceAsync {

  void loadContactList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<ContactDTO>> callback);
}
