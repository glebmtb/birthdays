package ru.n5g.birthdays.note_book.app_note_book.client.factory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.note_book.app_note_book.client.localization.AppNoteBookLocalization;
import ru.n5g.birthdays.note_book.app_note_book.client.service.AppNoteBookServiceAsync;
import ru.n5g.birthdays.note_book.app_note_book.client.view.AppNoteBookPage;
import ru.n5g.birthdays.core.client.factory.ClientFactory;
import ru.n5g.birthdays.note_book.contact.client.factory.ContactListFactory;
import ru.n5g.birthdays.note_book.event.client.factory.EventListFactory;

public interface AppNoteBookClientFactory extends ClientFactory {
  EventBus getEventBus();

  AppNoteBookPage getMainContainer();

  PlaceController getPlaceController();

  Place getDefaultPlace();

  AppNoteBookLocalization getLocalization();

  ContactListFactory getContactListFactory();

  AppNoteBookServiceAsync getService();

  EventListFactory getEventTypeListFactory();
}
