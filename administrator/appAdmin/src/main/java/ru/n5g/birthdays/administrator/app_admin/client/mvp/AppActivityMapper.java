package ru.n5g.birthdays.administrator.app_admin.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.n5g.birthdays.administrator.app_admin.client.factory.AppAdminClientFactory;

public class AppActivityMapper implements ActivityMapper {

  private AppAdminClientFactory factory;


  public AppActivityMapper(AppAdminClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public Activity getActivity(Place place) {
//    if(place instanceof UserListPlace){
//      factory.getMainContainer().setHeader(null, factory.getLocalization().contactHeader());
//      factory.getMainContainer().selectTab(AppAdminTabEnum.USER);
//      return new AppAdminActivity(factory, factory.getContactListFactory());
//    }
    return null;
  }
}
