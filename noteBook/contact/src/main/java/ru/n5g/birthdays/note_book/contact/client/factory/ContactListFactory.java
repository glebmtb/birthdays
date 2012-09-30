package ru.n5g.birthdays.note_book.contact.client.factory;

import ru.n5g.birthdays.note_book.contact.client.localization.ContactListLocalization;
import ru.n5g.birthdays.note_book.contact.client.presenter.ContactListPresenter;
import ru.n5g.birthdays.note_book.contact.client.service.ContactListServiceAsync;

public interface ContactListFactory {

  ContactListLocalization getLocalization();

  ContactListPresenter getPresenter();

  ContactListServiceAsync getService();

  ContactEditFactory getContactEditFactory();
}
