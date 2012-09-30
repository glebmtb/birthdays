package ru.n5g.birthdays.note_book.contact.server.dao;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.dao.BaseDao;

/**
 * @author belyaev
 */
public interface ContactListDao extends BaseDao<Contact> {
  List loadTableRows(BaseModelData filter);

  int getTableRowsCount(BaseModelData filter);
}
