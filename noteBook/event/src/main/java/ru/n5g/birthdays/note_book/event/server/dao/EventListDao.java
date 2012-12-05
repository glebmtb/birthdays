package ru.n5g.birthdays.note_book.event.server.dao;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import ru.n5g.birthdays.core.server.bean.EventListView;
import ru.n5g.birthdays.core.server.dao.BaseDao;

/**
 * @author belyaev
 */
public interface EventListDao extends BaseDao<EventListView> {
  int getTableRowsCount(BaseModelData filter);

  List loadTableRows(BaseModelData filter);
}
