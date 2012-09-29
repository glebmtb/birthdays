package ru.n5g.birthdays.administrator.app_admin.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.administrator.app_admin.client.localization.AppAdminLocalization;
import ru.n5g.birthdays.administrator.app_admin.client.service.AppAdminService;
import ru.n5g.birthdays.administrator.app_admin.client.service.AppAdminServiceAsync;
import ru.n5g.birthdays.administrator.app_admin.client.view.AppAdminPage;
import ru.n5g.birthdays.administrator.app_admin.client.view.AppAdminPageImpl;

public class AppAdminClientFactoryImpl implements AppAdminClientFactory {
  private static final EventBus eventBus = new SimpleEventBus();
  private AppAdminPage view;
  private AppAdminLocalization localization = GWT.create(AppAdminLocalization.class);
  private AppAdminServiceAsync service = GWT.create(AppAdminService.class);
  private static final PlaceController placeController = new PlaceController(eventBus);

//  private UserListFactory userListFactory;

  public EventBus getEventBus() {
    return eventBus;
  }

  @Override
  public AppAdminPage getMainContainer() {
    return view == null ? view = new AppAdminPageImpl(localization) : view;
  }

  @Override
  public PlaceController getPlaceController() {
    return placeController;
  }

  @Override
  public Place getDefaultPlace() {
    return null;
//    return new UserListPlace();
  }

  @Override
  public AppAdminLocalization getLocalization() {
    return localization;
  }

//  @Override
//  public UserListFactory getContactListFactory() {
//    if (userListFactory == null)
//      userListFactory = new UserListFactoryImpl(this);
//    return userListFactory;
//  }

  @Override
  public AppAdminServiceAsync getService() {
    return service;
  }
}
