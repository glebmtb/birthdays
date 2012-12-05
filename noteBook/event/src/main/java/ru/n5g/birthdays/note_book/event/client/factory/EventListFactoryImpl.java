package ru.n5g.birthdays.note_book.event.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.note_book.event.client.localization.EventListLocalization;
import ru.n5g.birthdays.note_book.event.client.presenter.EventListPresenter;
import ru.n5g.birthdays.note_book.event.client.service.EventListService;
import ru.n5g.birthdays.note_book.event.client.service.EventListServiceAsync;

/**
 * @author belyaev
 */
public class EventListFactoryImpl implements EventListFactory {
  private EventListLocalization localization = GWT.create(EventListLocalization.class);
  private EventListServiceAsync service = GWT.create(EventListService.class);
  private EventListPresenter presenter;

  @Override
  public EventListLocalization getLocalization() {
    return localization;
  }

  @Override
  public EventListPresenter getPresenter() {
    if (presenter == null)
      presenter = new EventListPresenter(this);
    return presenter;
  }

  @Override
  public EventListServiceAsync getService() {
    return service;
  }
}
