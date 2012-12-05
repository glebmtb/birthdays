package ru.n5g.birthdays.core.server.dao.combo_box;

import java.util.List;

import com.extjs.gxt.ui.client.Style;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilter;
import ru.n5g.birthdays.core.shared.data.Filter;

/**
 * @author belyaev
 */
public interface ComboBoxDao<T> {

  void setFilter(Filter filter);

  Integer getRowsCount(List<ComboBoxFilter> filters);

  List<T> loadRows(int fromRow, int rowCount, Style.SortDir sortDir, String orderField, List<ComboBoxFilter> filters);
}
