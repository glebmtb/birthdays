package ru.n5g.birthdays.administrator.app_admin.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.n5g.birthdays.administrator.app_admin.client.bean.AppAdminTabEnum;
import ru.n5g.birthdays.administrator.app_admin.client.factory.AppAdminClientFactory;
import ru.n5g.birthdays.administrator.event_type.client.activity.EventTypeListActivity;
import ru.n5g.birthdays.administrator.event_type.client.place.EventTypeListPlace;
import ru.n5g.birthdays.administrator.user.client.activity.UserListActivity;
import ru.n5g.birthdays.administrator.user.client.place.UserListPlace;

public class AppActivityMapper implements ActivityMapper {

  private AppAdminClientFactory factory;


  public AppActivityMapper(AppAdminClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public Activity getActivity(Place place) {
    if(place instanceof UserListPlace){
      factory.getMainContainer().setHeader(null, factory.getLocalization().contactHeader());
      factory.getMainContainer().selectTab(AppAdminTabEnum.USER);
      return new UserListActivity(factory, factory.getContactListFactory());
    }
    if(place instanceof EventTypeListPlace){
      factory.getMainContainer().setHeader(null, factory.getLocalization().contactHeader());
      factory.getMainContainer().selectTab(AppAdminTabEnum.EVENT_TYPE);
      return new EventTypeListActivity(factory, factory.getEventTypeListFactory());
    }
    return null;
  }
}
