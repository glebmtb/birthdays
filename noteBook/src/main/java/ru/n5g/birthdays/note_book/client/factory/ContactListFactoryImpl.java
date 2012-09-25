package ru.n5g.birthdays.note_book.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.core.client.factory.ClientFactory;
import ru.n5g.birthdays.note_book.client.localization.ContactListLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactListPresenter;
import ru.n5g.birthdays.note_book.client.service.ContactListService;
import ru.n5g.birthdays.note_book.client.service.ContactListServiceAsync;

public class ContactListFactoryImpl implements ContactListFactory {
  private final ContactListLocalization listLocalization = GWT.create(ContactListLocalization.class);
  private final ContactListServiceAsync service = GWT.create(ContactListService.class);
  private ContactListPresenter presenter;

  private ClientFactory clientFactory;

  public ContactListFactoryImpl(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  @Override
  public ContactListLocalization getListLocalization() {
    return listLocalization;
  }

  public ContactListPresenter getPresenter() {
    if (presenter == null)
      presenter = new ContactListPresenter(this);
    return presenter;
  }

  @Override
  public ContactListServiceAsync getService() {
    return service;
  }
}
