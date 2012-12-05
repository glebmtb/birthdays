package ru.n5g.birthdays.administrator.event_type.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.administrator.event_type.client.localization.EventTypeListLocalization;
import ru.n5g.birthdays.administrator.event_type.client.presenter.EventTypeListPresenter;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeListService;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeListServiceAsync;
import ru.n5g.birthdays.core.client.factory.ClientFactory;

public class EventTypeListFactoryImpl implements EventTypeListFactory {
  private final EventTypeListLocalization listLocalization = GWT.create(EventTypeListLocalization.class);
  private final EventTypeListServiceAsync service = GWT.create(EventTypeListService.class);
  private EventTypeListPresenter presenter;
  private EventTypeEditFactory contactEditFactory;

  private ClientFactory clientFactory;

  public EventTypeListFactoryImpl(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  @Override
  public EventTypeListLocalization getLocalization() {
    return listLocalization;
  }

  public EventTypeListPresenter getPresenter() {
    if (presenter == null)
      presenter = new EventTypeListPresenter(this);
    return presenter;
  }

  @Override
  public EventTypeListServiceAsync getService() {
    return service;
  }

  @Override
  public EventTypeEditFactory getContactEditFactory() {
    if (contactEditFactory == null)
      contactEditFactory = new EventTypeEditFactoryImpl();
    return contactEditFactory;
  }
}
