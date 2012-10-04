package ru.n5g.birthdays.note_book.event.client.factory;

import ru.n5g.birthdays.note_book.event.client.localization.EventTypeListLocalization;
import ru.n5g.birthdays.note_book.event.client.presenter.EventTypeListPresenter;
import ru.n5g.birthdays.note_book.event.client.service.EventTypeListServiceAsync;

public interface EventTypeListFactory {

  EventTypeListLocalization getLocalization();

  EventTypeListPresenter getPresenter();

  EventTypeListServiceAsync getService();
}
