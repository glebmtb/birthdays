package ru.n5g.birthdays.note_book.client.factory;

import ru.n5g.birthdays.note_book.client.localization.ContactLocalization;
import ru.n5g.birthdays.note_book.client.service.ContactServiceAsync;
import ru.n5g.birthdays.note_book.client.view.ContactView;

public interface ContactFactory {

  ContactLocalization getLocalization();

  ContactView.Presenter getPresenter();

  ContactServiceAsync getService();
}
