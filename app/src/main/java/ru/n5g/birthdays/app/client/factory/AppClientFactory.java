package ru.n5g.birthdays.app.client.factory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.administrator.client.factory.AdministratorFactory;
import ru.n5g.birthdays.app.client.localization.AppLocalization;
import ru.n5g.birthdays.app.client.view.AppPage;
import ru.n5g.birthdays.core.client.factory.ClientFactory;

public interface AppClientFactory  extends ClientFactory {
  EventBus getEventBus();

  AdministratorFactory getAdministratorFactory();

  AppPage getMainContainer();

  PlaceController getPlaceController();

  Place getDefaultPlace();

  AppLocalization getLocalization();
}
