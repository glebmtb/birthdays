package ru.n5g.birthdays.note_book.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;

@RemoteServiceRelativePath("contactListService.rpc")
public interface ContactListService extends RemoteService  {

  BasePagingLoadResult<ContactDTO> loadContactList(BasePagingLoadConfig loadConfig);

  void deleteContact(List<ContactDTO> dtoList);
}
