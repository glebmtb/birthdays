package ru.n5g.birthdays.note_book.contact.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;

/**
 * @author belyaev
 */
public interface ContactEditServiceAsync {
  void saveContact(ContactDTO dto, AsyncCallback<Void> callback);

  void getContact(Long id, AsyncCallback<ContactDTO> callback);
}
