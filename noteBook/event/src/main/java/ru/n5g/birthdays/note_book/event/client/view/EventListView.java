package ru.n5g.birthdays.note_book.event.client.view;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Element;
import ru.n5g.birthdays.note_book.event.client.localization.EventListLocalization;
import ru.n5g.birthdays.note_book.event.client.presenter.EventListPresenter;

/**
 * Список событий - GUI
 *
 * @author belyaev
 */
public class EventListView extends LayoutContainer implements EventListPresenter.View {

  public EventListView(EventListPresenter presenter, EventListLocalization localization) {

  }


  //Сделать поиск по ФИО
  //Выборка по Поводу
  //Подумать как сделать недавние события

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);

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
