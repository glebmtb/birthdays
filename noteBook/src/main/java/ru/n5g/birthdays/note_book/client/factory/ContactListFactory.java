package ru.n5g.birthdays.note_book.client.factory;

import ru.n5g.birthdays.note_book.client.localization.ContactListLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactListPresenter;
import ru.n5g.birthdays.note_book.client.service.ContactListServiceAsync;

public interface ContactListFactory {

  ContactListLocalization getListLocalization();

  ContactListPresenter getPresenter();

  ContactListServiceAsync getService();
}
