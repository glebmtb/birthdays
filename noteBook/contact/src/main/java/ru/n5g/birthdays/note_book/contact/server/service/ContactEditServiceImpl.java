package ru.n5g.birthdays.note_book.contact.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.ContactDao;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.note_book.contact.client.service.ContactEditService;

/**
 * @author belyaev
 */
@Service("contactEditService.rpc")
public class ContactEditServiceImpl implements ContactEditService {

  @Autowired
  private ContactDao contactDAO;

  @Override
  public void saveContact(ContactDTO dto) {
    Contact bean = Contact.convert(dto);
    bean.setUserId(User.getAuthenticationUserId());
    contactDAO.saveOrUpdate(bean);
  }
}
