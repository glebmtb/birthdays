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

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);

  }


  @Override
  public void onRefresh() {

  }
}
