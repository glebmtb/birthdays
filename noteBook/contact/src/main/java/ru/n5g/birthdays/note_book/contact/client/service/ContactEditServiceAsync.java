package ru.n5g.birthdays.note_book.contact.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author belyaev
 */
public interface ContactEditServiceAsync {
  void saveContact(ContactDTO dto, AsyncCallback<Void> callback);

  void getContact(Long id, AsyncCallback<ContactDTO> callback);

  void loadEventTypeList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<EventTypeDTO>> callback);
}
