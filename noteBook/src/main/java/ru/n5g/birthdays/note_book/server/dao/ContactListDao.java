package ru.n5g.birthdays.note_book.server.dao;

import java.util.List;

import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.dao.BaseDao;

/**
 * @author belyaev
 */
public interface ContactListDao extends BaseDao<Contact> {
  List<Contact> loadTableRows();

  int getTableRowsCount();
}
