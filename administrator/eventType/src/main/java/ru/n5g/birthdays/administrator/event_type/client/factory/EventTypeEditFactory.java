package ru.n5g.birthdays.administrator.event_type.client.factory;

import ru.n5g.birthdays.administrator.event_type.client.localization.EventTypeEditLocalization;
import ru.n5g.birthdays.administrator.event_type.client.presenter.EventTypeEditPresenter;
import ru.n5g.birthdays.administrator.event_type.client.service.EventTypeEditServiceAsync;

/**
 * @author belyaev
 */
public interface EventTypeEditFactory {
  EventTypeEditLocalization getLocalization();

  EventTypeEditPresenter getPresenter();

  EventTypeEditServiceAsync getService();
}
