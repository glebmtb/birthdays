package ru.n5g.birthdays.note_book.contact.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactListDTO;

@RemoteServiceRelativePath("contactListService.rpc")
public interface ContactListService extends RemoteService  {

  BasePagingLoadResult<ContactListDTO> loadContactList(BasePagingLoadConfig loadConfig);

  void deleteContact(List<ContactListDTO> dtoList);
}
