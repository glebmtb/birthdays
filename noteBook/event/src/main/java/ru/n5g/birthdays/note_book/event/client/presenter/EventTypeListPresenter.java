package ru.n5g.birthdays.note_book.event.client.presenter;

import com.google.gwt.user.client.ui.IsWidget;
import ru.n5g.birthdays.note_book.event.client.factory.EventTypeListFactory;

public class EventTypeListPresenter {
  private EventTypeListFactory factory;

  public EventTypeListPresenter(EventTypeListFactory factory) {
    this.factory = factory;
  }

  public View start() {
    return null;  //TODO: implement this method
  }

  public interface View extends IsWidget {
    void onRefresh();
  }
}
