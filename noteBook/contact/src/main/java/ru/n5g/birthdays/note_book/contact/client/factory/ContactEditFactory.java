package ru.n5g.birthdays.note_book.contact.client.factory;

import ru.n5g.birthdays.note_book.contact.client.localization.ContactEditLocalization;
import ru.n5g.birthdays.note_book.contact.client.presenter.ContactEditPresenter;
import ru.n5g.birthdays.note_book.contact.client.service.ContactEditServiceAsync;

/**
 * @author belyaev
 */
public interface ContactEditFactory {
  ContactEditLocalization getLocalization();

  ContactEditPresenter getPresenter();

  ContactEditServiceAsync getService();
}
