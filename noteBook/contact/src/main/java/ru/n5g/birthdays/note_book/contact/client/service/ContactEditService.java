package ru.n5g.birthdays.note_book.contact.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;

/**
 * @author belyaev
 */
@RemoteServiceRelativePath("contactEditService.rpc")
public interface ContactEditService extends RemoteService {
  void saveContact(ContactDTO dto);
}
