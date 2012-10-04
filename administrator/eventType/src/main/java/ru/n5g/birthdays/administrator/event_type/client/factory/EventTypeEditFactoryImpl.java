package ru.n5g.birthdays.administrator.event_type.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.administrator.event_type.client.localization.EventTypeEditLocalization;
import ru.n5g.birthdays.administrator.event_type.client.presenter.EventTypeEditPresenter;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeEditService;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeEditServiceAsync;

/**
 * @author belyaev
 */
public class EventTypeEditFactoryImpl implements EventTypeEditFactory {
  private final EventTypeEditLocalization localization = GWT.create(EventTypeEditLocalization.class);
  private final EventTypeEditServiceAsync service = GWT.create(EventTypeEditService.class);
  private EventTypeEditPresenter presenter;

  @Override
  public EventTypeEditLocalization getLocalization() {
    return localization;
  }

  @Override
  public EventTypeEditServiceAsync getService() {
    return service;
  }

  @Override
  public EventTypeEditPresenter getPresenter() {
    if (presenter == null)
      presenter = new EventTypeEditPresenter(this);
    return presenter;
  }
}
