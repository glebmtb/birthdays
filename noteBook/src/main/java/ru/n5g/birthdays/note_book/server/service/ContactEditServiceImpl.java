package ru.n5g.birthdays.note_book.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.AppUserDetails;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.ContactDao;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.note_book.client.service.ContactEditService;

/**
 * @author belyaev
 */
@Service("contactEditService.rpc")
public class ContactEditServiceImpl implements ContactEditService {

  @Autowired
  private ContactDao contactDAO;

  @Override
  public void saveContact(ContactDTO dto) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    AppUserDetails appUserDetails = (AppUserDetails) auth.getPrincipal();
    User user = appUserDetails.getUser();
    Contact bean = Contact.convert(dto);
    bean.setUserId(user.getId());
    contactDAO.saveOrUpdate(bean);
  }
}
