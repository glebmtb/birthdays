package ru.n5g.birthdays.app.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.app.client.localization.AppLocalization;
import ru.n5g.birthdays.app.client.view.AppPage;
import ru.n5g.birthdays.app.client.view.AppPageImpl;

public class AppClientFactoryImpl implements AppClientFactory {
  private static final EventBus eventBus = new SimpleEventBus();
  private AppPage view;
  private AppLocalization localization = GWT.create(AppLocalization.class);
  private static final PlaceController placeController = new PlaceController(eventBus);

  public EventBus getEventBus() {
    return eventBus;
  }

  @Override
  public AppPage getMainContainer() {
    return view == null ? view = new AppPageImpl(localization) : view;
  }

  @Override
  public PlaceController getPlaceController() {
    return placeController;
  }

  @Override
  public Place getDefaultPlace() {
    return null;  //TODO: implement this method
  }
}
