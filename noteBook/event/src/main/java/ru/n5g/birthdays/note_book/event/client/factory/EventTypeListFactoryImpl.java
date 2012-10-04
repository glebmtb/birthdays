package ru.n5g.birthdays.note_book.event.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.note_book.event.client.localization.EventTypeListLocalization;
import ru.n5g.birthdays.note_book.event.client.presenter.EventTypeListPresenter;
import ru.n5g.birthdays.note_book.event.client.service.EventTypeListService;
import ru.n5g.birthdays.note_book.event.client.service.EventTypeListServiceAsync;

/**
 * @author belyaev
 */
public class EventTypeListFactoryImpl implements EventTypeListFactory {
  private EventTypeListLocalization localization = GWT.create(EventTypeListLocalization.class);
  private EventTypeListServiceAsync service = GWT.create(EventTypeListService.class);
  private EventTypeListPresenter presenter;

  @Override
  public EventTypeListLocalization getLocalization() {
    return localization;
  }

  @Override
  public EventTypeListPresenter getPresenter() {
    if (presenter == null)
      presenter = new EventTypeListPresenter(this);
    return presenter;
  }

  @Override
  public EventTypeListServiceAsync getService() {
    return service;
  }
}
