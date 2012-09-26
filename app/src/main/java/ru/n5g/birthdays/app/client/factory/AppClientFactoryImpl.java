package ru.n5g.birthdays.app.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.app.client.localization.AppLocalization;
import ru.n5g.birthdays.app.client.view.AppPage;
import ru.n5g.birthdays.app.client.view.AppPageImpl;
import ru.n5g.birthdays.note_book.client.factory.ContactListFactory;
import ru.n5g.birthdays.note_book.client.factory.ContactListFactoryImpl;
import ru.n5g.birthdays.note_book.client.place.ContactListPlace;

public class AppClientFactoryImpl implements AppClientFactory {
  private static final EventBus eventBus = new SimpleEventBus();
  private AppPage view;
  private AppLocalization localization = GWT.create(AppLocalization.class);
  private static final PlaceController placeController = new PlaceController(eventBus);

  private ContactListFactory contactListFactory;

  public EventBus getEventBus() {
    return eventBus;
  }

  @Override
  public AppPage getMainContainer() {
    return view == null ? view = new AppPageImpl(localization) : view;
  }

  @Override
  public PlaceController getPlaceController() {
    return placeController;
  }

  @Override
  public Place getDefaultPlace() {
    return new ContactListPlace();
  }

  @Override
  public AppLocalization getLocalization() {
    return localization;
  }

  @Override
  public ContactListFactory getContactListFactory() {
    if (contactListFactory == null)
      contactListFactory = new ContactListFactoryImpl(this);
    return contactListFactory;
  }
}
