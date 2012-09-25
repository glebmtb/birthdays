package ru.n5g.birthdays.note_book.client.factory;

import ru.n5g.birthdays.note_book.client.localization.ContactEditLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactEditPresenter;
import ru.n5g.birthdays.note_book.client.service.ContactEditServiceAsync;

/**
 * @author belyaev
 */
public interface ContactEditFactory {
  ContactEditLocalization getLocalization();

  ContactEditPresenter getPresenter();

  ContactEditServiceAsync getService();
}
