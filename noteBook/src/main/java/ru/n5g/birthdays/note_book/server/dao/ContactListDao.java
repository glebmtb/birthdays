package ru.n5g.birthdays.note_book.server.dao;

import java.util.List;

import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.dao.ContactDao;

/**
 * @author belyaev
 */
public interface ContactListDao extends ContactDao {
  List<Contact> loadTableRows();

  int getTableRowsCount();
}
