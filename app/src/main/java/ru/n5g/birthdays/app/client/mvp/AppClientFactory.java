package ru.n5g.birthdays.app.client.mvp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.app.client.view.AppPage;

public interface AppClientFactory {
  EventBus getEventBus();

  AppPage getMainContainer();

  PlaceController getPlaceController();

  Place getDefaultPlace();
}
