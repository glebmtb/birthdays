package ru.n5g.birthdays.app.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.n5g.birthdays.administrator.client.activity.AdministratorActivity;
import ru.n5g.birthdays.administrator.client.place.AdministratorPlace;
import ru.n5g.birthdays.app.client.factory.AppClientFactory;
import ru.n5g.birthdays.core.shared.TabEnum;

public class AppActivityMapper implements ActivityMapper {

  private AppClientFactory factory;


  public AppActivityMapper(AppClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public Activity getActivity(Place place) {
    if(place instanceof AdministratorPlace){
      factory.getMainContainer().setHeader(null, factory.getLocalization().administratorHeader());
      factory.getMainContainer().selectTab(TabEnum.ADMINISTRATOR);
      return new AdministratorActivity(factory, factory.getAdministratorFactory());
    }
    return null;
  }
}
