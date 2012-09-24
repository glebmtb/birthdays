package ru.n5g.birthdays.note_book.client.factory;

import com.google.gwt.core.client.GWT;
import ru.n5g.birthdays.core.client.factory.ClientFactory;
import ru.n5g.birthdays.note_book.client.localization.ContactLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactPresenter;
import ru.n5g.birthdays.note_book.client.service.ContactService;
import ru.n5g.birthdays.note_book.client.service.ContactServiceAsync;
import ru.n5g.birthdays.note_book.client.view.ContactView;

public class ContactFactoryImpl implements ContactFactory {
  private final ContactLocalization localization = GWT.create(ContactLocalization.class);
  private final ContactServiceAsync service = GWT.create(ContactService.class);
  private ContactView.Presenter presenter;

  private ClientFactory clientFactory;

  public ContactFactoryImpl(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  @Override
  public ContactLocalization getLocalization() {
    return localization;
  }

  public ContactView.Presenter getPresenter() {
    if (presenter == null)
      presenter = new ContactPresenter(this);
    return presenter;
  }

  @Override
  public ContactServiceAsync getService() {
    return service;
  }
}
