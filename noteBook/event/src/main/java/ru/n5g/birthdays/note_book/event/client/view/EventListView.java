package ru.n5g.birthdays.note_book.event.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import ru.n5g.birthdays.components.client.view.SimpleCreateField;
import ru.n5g.birthdays.note_book.event.client.localization.EventListLocalization;
import ru.n5g.birthdays.note_book.event.client.presenter.EventListPresenter;
import ru.n5g.birthdays.note_book.event.shared.bean.EventListDTO;

/**
 * Список событий - GUI
 *
 * @author belyaev
 */
public class EventListView extends LayoutContainer implements EventListPresenter.View {
  private EventListLocalization localization;
  private EventListPresenter presenter;

  public EventListView(EventListPresenter presenter, EventListLocalization localization) {
    this.localization = localization;
    this.presenter = presenter;

  }


  //Сделать поиск по ФИО
  //Выборка по Поводу
  //Подумать как сделать недавние события

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);
    setLayout(new FitLayout());
    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
    columns.add(SimpleCreateField.columnConfigSortable(EventListDTO.EVENT_DAYS_LEFT, "Дней", 150, true));
    columns.add(SimpleCreateField.columnConfigSortable(EventListDTO.CONTACT_FIO, "Фио", 150, true));
    ColumnModel cm = new ColumnModel(columns);
    ListStore<EventListDTO> ls = presenter.loadList();

    Grid<EventListDTO> grid = new Grid<EventListDTO>(ls, cm);
    grid.getView().setEmptyText("Пусто  ");
    add(grid);

    //Вывести список событий
    //Сделать три колонки
    //Дней до события (сортировать по возрастанию по умолчанию) пример "22д. [22 мая 1988 (22г.)]"
    //Повод "День рождения"
    //Фио "(Друган) Андреева Катерина Петровна"
  }


  @Override
  public void onRefresh() {
    //Обновить список событий
  }
}
