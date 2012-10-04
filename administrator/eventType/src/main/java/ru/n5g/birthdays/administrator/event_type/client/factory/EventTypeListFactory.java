package ru.n5g.birthdays.administrator.event_type.client.factory;

import ru.n5g.birthdays.administrator.event_type.client.localization.EventTypeListLocalization;
import ru.n5g.birthdays.administrator.event_type.client.presenter.EventTypeListPresenter;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeListServiceAsync;

public interface EventTypeListFactory {

  EventTypeListLocalization getLocalization();

  EventTypeListPresenter getPresenter();

  EventTypeListServiceAsync getService();

  EventTypeEditFactory getContactEditFactory();
}
