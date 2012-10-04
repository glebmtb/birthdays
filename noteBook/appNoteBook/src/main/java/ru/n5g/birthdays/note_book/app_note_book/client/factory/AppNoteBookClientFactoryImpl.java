package ru.n5g.birthdays.note_book.app_note_book.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.note_book.app_note_book.client.localization.AppNoteBookLocalization;
import ru.n5g.birthdays.note_book.app_note_book.client.service.AppNoteBookService;
import ru.n5g.birthdays.note_book.app_note_book.client.service.AppNoteBookServiceAsync;
import ru.n5g.birthdays.note_book.app_note_book.client.view.AppNoteBookPage;
import ru.n5g.birthdays.note_book.app_note_book.client.view.AppNoteBookPageImpl;
import ru.n5g.birthdays.note_book.contact.client.factory.ContactListFactory;
import ru.n5g.birthdays.note_book.contact.client.factory.ContactListFactoryImpl;
import ru.n5g.birthdays.note_book.contact.client.place.ContactListPlace;
import ru.n5g.birthdays.note_book.event.client.factory.EventTypeListFactory;
import ru.n5g.birthdays.note_book.event.client.factory.EventTypeListFactoryImpl;

public class AppNoteBookClientFactoryImpl implements AppNoteBookClientFactory {
  private static final EventBus eventBus = new SimpleEventBus();
  private AppNoteBookPage view;
  private AppNoteBookLocalization localization = GWT.create(AppNoteBookLocalization.class);
  private AppNoteBookServiceAsync service = GWT.create(AppNoteBookService.class);
  private static final PlaceController placeController = new PlaceController(eventBus);

  private ContactListFactory contactListFactory;
  private EventTypeListFactory eventTypeListFactory;

  public EventBus getEventBus() {
    return eventBus;
  }

  @Override
  public AppNoteBookPage getMainContainer() {
    return view == null ? view = new AppNoteBookPageImpl(localization) : view;
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
  public AppNoteBookLocalization getLocalization() {
    return localization;
  }

  @Override
  public ContactListFactory getContactListFactory() {
    if (contactListFactory == null)
      contactListFactory = new ContactListFactoryImpl();
    return contactListFactory;
  }

  @Override
  public AppNoteBookServiceAsync getService() {
    return service;
  }

  @Override
  public EventTypeListFactory getEventTypeListFactory() {
    if (eventTypeListFactory == null)
      eventTypeListFactory = new EventTypeListFactoryImpl();
    return eventTypeListFactory;
  }
}
