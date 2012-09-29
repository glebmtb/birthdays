package ru.n5g.birthdays.administrator.app_admin.client.factory;

import ru.n5g.birthdays.administrator.user.client.factory.UserListFactory;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import ru.n5g.birthdays.administrator.app_admin.client.localization.AppAdminLocalization;
import ru.n5g.birthdays.administrator.app_admin.client.service.AppAdminServiceAsync;
import ru.n5g.birthdays.administrator.app_admin.client.view.AppAdminPage;
import ru.n5g.birthdays.core.client.factory.ClientFactory;

public interface AppAdminClientFactory extends ClientFactory {
  EventBus getEventBus();

  AppAdminPage getMainContainer();

  PlaceController getPlaceController();

  Place getDefaultPlace();

  AppAdminLocalization getLocalization();

//  UserListFactory getContactListFactory();

  AppAdminServiceAsync getService();
}
