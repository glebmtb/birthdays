package ru.n5g.birthdays.note_book.contact.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactListDTO;

public interface ContactListServiceAsync {

  void loadContactList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<ContactListDTO>> callback);

  void deleteContact(List<ContactListDTO> dtoList, AsyncCallback<Void> callback);
}
