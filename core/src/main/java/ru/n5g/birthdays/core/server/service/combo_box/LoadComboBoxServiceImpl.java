package ru.n5g.birthdays.core.server.service.combo_box;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseListFilterConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.core.server.dao.combo_box.ComboBoxDao;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilter;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilterType;

/**
 * @author belyaev
 */
@Service
public class LoadComboBoxServiceImpl<T extends ModelData> implements LoadComboBoxService {
  private static final long serialVersionUID = 3668731396641477868L;
  protected ComboBoxDao comboBoxDao;
  private List<ComboBoxFilter> filterList = new ArrayList<ComboBoxFilter>();

  @Autowired
  public void setComboBoxDao(ComboBoxDao comboBoxDao) {
    this.comboBoxDao = comboBoxDao;
  }

  @Override
  public BasePagingLoadResult<T> load(BasePagingLoadConfig pagingLoadConfig) {
    return load(pagingLoadConfig , null);
  }

  /**
   *
   * @param pagingLoadConfig
   * @param filters
   * @return
   */
  public BasePagingLoadResult load(BasePagingLoadConfig pagingLoadConfig, List filters) {

    System.out.println("in load");

    if (filters == null) {
      filters = new ArrayList<ComboBoxFilter>();
    }
    if (pagingLoadConfig.get("filters") != null) {
      ArrayList<BaseListFilterConfig> filterList = (ArrayList<BaseListFilterConfig>) pagingLoadConfig.get("filters");
      for (BaseListFilterConfig filterConfig : filterList) {
        ComboBoxFilter filter = new ComboBoxFilter(filterConfig.getField(), filterConfig.getValue(), ComboBoxFilterType.valueOf(filterConfig.getComparison()));
        filters.add(filter);
      }
    }
    if (filterList != null) {
      filters.addAll(filterList);
    }

    int count = comboBoxDao.getRowsCount(filters);

    if (pagingLoadConfig != null) {
      System.out.println("LoadConfig : offset " + pagingLoadConfig.getOffset() + " <> limit : "
          + pagingLoadConfig.getLimit());
    }

    String sortField = pagingLoadConfig.getSortField();
    Style.SortDir sortDir = pagingLoadConfig.getSortDir();

    int start = -1;
    int limit = -1;
    int offsetLimit = -1;

    if (pagingLoadConfig != null) {
      start = pagingLoadConfig.getOffset();
      limit = count;
      offsetLimit = pagingLoadConfig.getLimit();
    }
    else {
      start = 0;
      limit = count;
      offsetLimit = 0;
    }

    System.out.println("Start : " + start + " limit : " + limit + " offsetLimit : " + offsetLimit);
    if (offsetLimit > 0) {
      limit = Math.min(start + offsetLimit, limit);
    }

    List datas = comboBoxDao.loadRows(start, offsetLimit, sortDir, sortField, filters);
    List<T> agentModelList = getModelList(datas);

    System.out.println("AgentList Size : " + agentModelList.size());
    BasePagingLoadResult<T> basePagingLoadResult = new BasePagingLoadResult<T>(agentModelList, start, count);
    System.out.println("BasePagingLoadResult Size : " + basePagingLoadResult.getTotalLength());

    return basePagingLoadResult;
  }

  /**
   * Получить список моделей для таблицы или другого компонента из списка бинов, полученных из дао.
   * По умолчанию считается, что модели и бины из дао - один и тот же класс. Если это не так,
   * метод надо перегрузить.
   * @param dataList список бинов, полученных из дао методом loadRows
   * @return
   */
  protected List<T> getModelList(List dataList) {
    return dataList;
  }

}
