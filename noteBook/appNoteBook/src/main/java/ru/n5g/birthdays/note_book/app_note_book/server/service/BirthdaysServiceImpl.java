package ru.n5g.birthdays.note_book.app_note_book.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.dao.ContactDao;

@Service
public class BirthdaysServiceImpl implements BirthdaysService {
  @Autowired
  private ContactDao contactDAO;

  public String getHelloWorld() {
    Contact contact = contactDAO.get(new Long(1));
    if (contact == null)
      return "Пусто";
    return contact.getFirstName();
  }

  @Override
  public String getPeople(Integer id) {
    String s = "";
    if (id == null) {
      s = "Ид не введено";
      return s;
    }
    Contact contact = contactDAO.get(new Long(id));
    if (contact == null) {
      s = "С таким Id пользователь не существует.";
    }
    if (contact != null) {
      s = contact.getLastName() + " " + contact.getFirstName();
    }
    return s;
  }
}
