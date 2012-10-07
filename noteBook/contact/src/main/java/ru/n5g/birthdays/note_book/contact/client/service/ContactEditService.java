package ru.n5g.birthdays.note_book.contact.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactEditDTO;
import ru.n5g.birthdays.note_book.contact.shared.bean.RpcWhiteListContact;

/**
 * @author belyaev
 */
@RemoteServiceRelativePath("contactEditService.rpc")
public interface ContactEditService extends RemoteService {
  void saveContact(ContactDTO dto);

  ContactEditDTO getContact(Long id);

  BasePagingLoadResult<EventTypeDTO> loadEventTypeList(BasePagingLoadConfig loadConfig);

  RpcWhiteListContact registerClasses(RpcWhiteListContact whiteList);
}
