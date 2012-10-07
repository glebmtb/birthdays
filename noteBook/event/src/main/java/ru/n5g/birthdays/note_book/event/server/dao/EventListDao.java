package ru.n5g.birthdays.note_book.event.server.dao;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import ru.n5g.birthdays.core.server.bean.Event;
import ru.n5g.birthdays.core.server.dao.BaseDao;

/**
 * @author belyaev
 */
public interface EventListDao extends BaseDao<Event> {
  int getTableRowsCount(BaseModelData filter);

  List loadTableRows(BaseModelData filter);
}
