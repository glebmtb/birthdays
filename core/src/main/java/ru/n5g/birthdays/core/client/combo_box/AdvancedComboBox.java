package ru.n5g.birthdays.core.client.combo_box;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseListFilterConfig;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.core.client.page.localization.AdvancedComboBoxLocalization;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilter;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilterType;

/**
 * @author belyaev
 */
public class AdvancedComboBox<T extends ModelData> extends ComboBox<T> {

  final static private AdvancedComboBoxLocalization localization = GWT.create(AdvancedComboBoxLocalization.class);
  public static final int PAGE_SIZE = 50;
  public static final int MIN_CHARS_FOR_QUERY = 3;

  /**
   * Текст в комбобоксе в состоянии инициализации
   */
  private String initializingText = localization.Initializing();

  /**
   * Фильтруемое поле. Если не задано, берётся значение getDisplayField() (по умолчанию не задано).
   */
  private String filterField;

  /**
   * наименование поля в хиб. бина по которому необходима сортировка
   */
  private String sortField;

  /**
   * порядок сортировки
   */
  private Style.SortDir sortDir;

  /**
   * Список фильтров.
   */
  private List<ComboBoxFilter> filters;

  /**
   * Тип фильтрации удаленного списка при вводе, по-умолчанию ComboBoxFilterType.ILIKE.
   */
  private ComboBoxFilterType remoteFilterType = ComboBoxFilterType.ILIKE;

  /**
   * Конструктор создания редактируемого поискового комбобокса со списком
   */
  public AdvancedComboBox() {
    this(true);
  }

  /**
   * Конструктор создания поискового комбобокса со списком
   *
   * @param editable - статус редактирования (ввода значения в поле)
   */
  public AdvancedComboBox(boolean editable) {
    super();

    this.filters = new ArrayList<ComboBoxFilter>();

    setDisplayField("name");
    setForceSelection(true);
    setTriggerAction(ComboBox.TriggerAction.ALL);
    setEditable(editable);
    setPageSize(PAGE_SIZE);
    setMinChars(MIN_CHARS_FOR_QUERY);

    setSortField(getDisplayField());
    setSortDir(Style.SortDir.ASC);

    this.setEmptyText(initializingText);
  }

  /**
   * Получить текст в комбобоксе в состоянии инициализации
   *
   * @return возвращает текст initializingText
   */
  public String getInitializingText() {
    return initializingText;
  }

  /**
   * Задать текст в комбобоксе в состоянии инициализации
   *
   * @param initializingText - текст в комбобоксе в состоянии инициализации
   */
  public void setInitializingText(String initializingText) {
    if (getEmptyText().equals(this.initializingText)) {
      setEmptyText(initializingText);
    }
    this.initializingText = initializingText;
  }

  /**
   * Задать фильтруемое поле.
   *
   * @param filterField фильтруемое поле. Если равно null, то фильтроваться будет по полю getDisplayField()
   */
  public void setFilterField(String filterField) {
    this.filterField = filterField;
  }

  /**
   * Установить наименование поля в хиб. бина по которому необходима сортировка
   *
   * @param sortField - наименование поля в хиб. бина по которому необходима сортировка
   */
  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  /**
   * Устанвоить порядок сортировки
   *
   * @param sortDir - порядок сортировки
   */
  public void setSortDir(Style.SortDir sortDir) {
    this.sortDir = sortDir;
  }

  /**
   * Задать список фильтров
   *
   * @param filters - список фильтров для подгрузки значений
   */
  public void setFilterList(List<ComboBoxFilter> filters) {
    this.filters = filters;
  }

  /**
   * Получить список фильтров
   *
   * @return возвращает список фильтров для подгрузки значений
   */
  public List<ComboBoxFilter> getFilterList() {
    return filters;
  }

  /**
   * Добавить новый фильтр
   *
   * @param filter - добавить фильтр
   */
  public void addFilter(ComboBoxFilter filter) {
    if (filters != null) {
      filters.add(filter);
    }
  }

  /**
   * Получить фильтр по наименованию поля
   *
   * @param field - наименование поля для фильтрации
   * @return возвращает фильтр по наименованию поля фильтрации
   */
  public ComboBoxFilter getFilter(String field) {
    if (filters != null) {
      for (ComboBoxFilter filter : filters) {
        if (filter.getField().equals(field)) {
          return filter;
        }
      }
    }
    return null;
  }

  /**
   * Получить тип фильтрации удаленного списка при вводе
   *
   * @return возвращает тип фильтрации удаленного списка при вводе
   */
  public ComboBoxFilterType getRemoteFilterType() {
    return remoteFilterType;
  }

  /**
   * Задать тип фильтрации удаленного списка при вводе
   *
   * @param remoteFilterType - тип фильтрации удаленного списка при вводе
   */
  public void setRemoteFilterType(ComboBoxFilterType remoteFilterType) {
    this.remoteFilterType = remoteFilterType;
  }

  /**
   * Принудительная подгрузка данных.
   * Принудительность заключается в том, что перед подгрузкой изменяем значение lastQuery
   */
  public void forceDoQuery() {
    lastQuery = "forceDoQuery";
    doQuery(null, true);
  }

  /**
   * Установить текст запроса для подгрузки данных
   *
   * @param query - текст запроса
   * @return вовзарщает конфигурационный класс загрузчик PagingLoadConfig
   */
  @Override
  protected PagingLoadConfig getParams(String query) {
    PagingLoadConfig config = super.getParams(query);
    List<FilterConfig> fconfigs = new ArrayList<FilterConfig>();
    FilterConfig fc = new BaseListFilterConfig();
    fc.setField(getFilterField());
    fc.setValue(getFilterValue());
    fc.setComparison(remoteFilterType.toString());
    fconfigs.add(fc);

    if (filters != null) {
      for (ComboBoxFilter filter : filters) {
        fc = new BaseListFilterConfig();
        fc.setField(filter.getField());
        fc.setValue(filter.getValue());
        fc.setComparison(filter.getFilterType().toString());
        fconfigs.add(fc);
      }
    }

    config.set("filters", fconfigs);
    config.setSortField(sortField);
    config.setSortDir(sortDir);
    return config;
  }

  /**
   * Получить фильтруемое поле. Если не задано, берётся значение getDisplayField() (по умолчанию не задано).
   *
   * @return фильтруемое поле
   */
  public String getFilterField() {
    return filterField != null ? filterField : getDisplayField();
  }

  /**
   * Получить значение для фильтра.
   *
   * @return возвращаем введенный текст
   */
  public String getFilterValue() {
    return lastQuery;
  }

  /**
   * Отображать тултипы в выпадающем списке.
   * Задаётся новый шаблон для элементов выпадающего списка, который включает себя тултипы.
   */
  public void setShowTooltipsTemplate() {
    setTemplate(getTooltipTemplate(getDisplayField()));
  }

  /**
   * Получить шаблон для выпадающих списков, чтобы отображались тултипы
   *
   * @param displayField отображаемое поле объекта
   * @return шаблон выпадающего списка
   */
  public String getTooltipTemplate(String displayField) {
    return "<tpl for=\".\"><div class=x-combo-list-item qtip='{" + displayField + "}'>{" + displayField + "}</div></tpl>";
  }

  @Override
  public void doQuery(String q, boolean forceAll) {
    if(q==null || q.isEmpty()){
      v = getValue();
    }else{
      v = null;
    }
    super.doQuery(q, forceAll);
  }

  T v;

  @Override
  public void setStore(ListStore<T> tListStore) {
    super.setStore(tListStore);
    tListStore.addStoreListener(new StoreListener<T>(){
      @Override
      public void storeDataChanged(StoreEvent<T> se) {
        if(v == null){
          return;
        }
        if(store.findModel(getFilterField(), v.get(getFilterField())) == null){
          store.insert(v, 0);
        }
      }
    });
  }

  /**
   * Добавить модель в store. Добавляется только в случае, если её там еще нет.
   *
   * @param model Добавляемая модель.
   */
  private void insertInStore(T model) {
    if (model == null || store == null) {
      return;
    }

    boolean notPresentInStore = false;
    String modelDisplayValue = model.get(getDisplayField()) != null ? model.get(getDisplayField()).toString() : null;
    if (modelDisplayValue != null) {
      notPresentInStore = store.findModel(getDisplayField(), modelDisplayValue) == null;
    }
    else {
      notPresentInStore = store.findModel(model) == null;
    }

    if (notPresentInStore) {
      store.insert(model, 0);
    }
  }

  /**
   * Установить значение.
   *
   * При установке значения модель нужно добавлять в store, иначе, если его в нём нет,
   * при потере фокуса комбобокс станет невалидным.
   *
   * @param value
   */
  @Override
  public void setValue(T value) {
    super.setValue(value);
    insertInStore(value);
  }
}
