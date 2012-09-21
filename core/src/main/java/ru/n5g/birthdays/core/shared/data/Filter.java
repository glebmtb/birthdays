package ru.n5g.birthdays.core.shared.data;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author belyaev
 */
public class Filter extends BaseModel implements IsSerializable {

  public static final String FILTER_FIELD_TRIGGER = "filterFieldTrigger";
  public static final String FROMROW = "fromrow";
  public static final String ROWCOUNT = "rowcount";
  public static final String SORTDIR = "sortdir";
  public static final String ORDERFIELD = "orderfield";

  /**
   * Если в фильтре будет содержаться данное поле со значением TRUE,
   * то вызовется метод getModelList с параметром фильтра
   */
  public static final String GET_MODEL_LIST_WITH_FILTER = "getModelListWithFilter";

  /**
   * Фильтр
   */
  public Filter() {
    super();
  }

  /**
   * Фильтр
   *
   * @param fromRow    - номер первой строки нужного куска выборки (нумерация с 0)
   * @param rowCount   - количество строк в нужном куске выборки
   * @param sortDir    -  enum перечисление сортировки по направлениям
   * @param orderField - поле которое нужно отсортировать
   */
  public Filter(Integer fromRow, Integer rowCount, Style.SortDir sortDir, String orderField) {
    this();
    setFromRow(fromRow);
    setRowCount(rowCount);
    setSortDir(sortDir);
    setOrderField(orderField);
  }

  /**
   * Фильтр
   *
   * @param fromRow            - номер первой строки нужного куска выборки (нумерация с 0)
   * @param rowCount           - количество строк в нужном куске выборки
   * @param sortDir            -  enum перечисление сортировки по направлениям
   * @param orderField         - поле которое нужно отсортировать
   * @param filterFieldTrigger - значение для фильтрации
   */
  public Filter(Integer fromRow, Integer rowCount, Style.SortDir sortDir, String orderField, String filterFieldTrigger) {
    this();
    setFromRow(fromRow);
    setRowCount(rowCount);
    setSortDir(sortDir);
    setOrderField(orderField);
    setFilterFieldTrigger(filterFieldTrigger);
  }

  /**
   * Получить номер первой строки нужного куска выборки (нумерация с 0)
   *
   * @return
   */
  public Integer getFromRow() {
    return get(FROMROW);
  }

  /**
   * Установить номер первой строки нужного куска выборки (нумерация с 0)
   *
   * @param fromRow
   */
  public void setFromRow(Integer fromRow) {
    set(FROMROW, fromRow);
  }

  /**
   * Получить количество строк в нужном куске выборки
   *
   * @return
   */
  public Integer getRowCount() {
    return get(ROWCOUNT);
  }

  /**
   * Установить количество строк в нужном куске выборки
   *
   * @param rowCount
   */
  public void setRowCount(Integer rowCount) {
    set(ROWCOUNT, rowCount);
  }

  /**
   * Получить enum перечисление сортировки по направлениям
   *
   * @return
   */
  public Style.SortDir getSortDirt() {
    return get(SORTDIR);
  }

  /**
   * Установить enum перечисление сортировки по направлениям
   *
   * @param sortDir
   */
  public void setSortDir(Style.SortDir sortDir) {
    set(SORTDIR, sortDir);
  }

  /**
   * Получить поле которое нужно отсортировать
   *
   * @return
   */
  public String getOrderField() {
    return get(ORDERFIELD);
  }

  /**
   * Установить поле которое нужно отсортировать
   *
   * @param orderField
   */
  public void setOrderField(String orderField) {
    set(ORDERFIELD, orderField);
  }

  /**
   * Получить значение для выбора данных, т.е. значение-условие поиска
   *
   * @return
   */
  public String getFilterFieldTrigger() {
    return get(FILTER_FIELD_TRIGGER);
  }

  /**
   * Установить значение для выбора данных, т.е. значение-условие поиска
   *
   * @param filterFieldTrigger
   */
  public void setFilterFieldTrigger(String filterFieldTrigger) {
    set(FILTER_FIELD_TRIGGER, filterFieldTrigger);
  }

}
