package ru.n5g.birthdays.administrator.event_type.server.dao;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import ru.n5g.birthdays.core.server.bean.EventType;
import ru.n5g.birthdays.core.server.dao.BaseDao;

/**
 * @author belyaev
 */
public interface EventTypeListDao extends BaseDao<EventType> {
  List loadTableRows(BaseModelData filter);

  int getTableRowsCount(BaseModelData filter);
}
