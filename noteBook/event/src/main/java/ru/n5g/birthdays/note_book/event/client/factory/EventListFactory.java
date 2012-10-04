package ru.n5g.birthdays.note_book.event.client.factory;

import ru.n5g.birthdays.note_book.event.client.localization.EventListLocalization;
import ru.n5g.birthdays.note_book.event.client.presenter.EventListPresenter;
import ru.n5g.birthdays.note_book.event.client.service.EventListServiceAsync;

public interface EventListFactory {

  EventListLocalization getLocalization();

  EventListPresenter getPresenter();

  EventListServiceAsync getService();
}
