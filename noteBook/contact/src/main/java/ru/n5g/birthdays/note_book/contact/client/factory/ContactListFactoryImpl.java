package ru.n5g.birthdays.note_book.contact.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.note_book.contact.client.localization.ContactListLocalization;
import ru.n5g.birthdays.note_book.contact.client.presenter.ContactListPresenter;
import ru.n5g.birthdays.note_book.contact.client.service.ContactListService;
import ru.n5g.birthdays.note_book.contact.client.service.ContactListServiceAsync;

public class ContactListFactoryImpl implements ContactListFactory {
  private final ContactListLocalization listLocalization = GWT.create(ContactListLocalization.class);
  private final ContactListServiceAsync service = GWT.create(ContactListService.class);
  private ContactListPresenter presenter;
  private ContactEditFactory contactEditFactory;

  @Override
  public ContactListLocalization getLocalization() {
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

  @Override
  public ContactEditFactory getContactEditFactory() {
    if (contactEditFactory == null)
      contactEditFactory = new ContactEditFactoryImpl();
    return contactEditFactory;
  }
}
