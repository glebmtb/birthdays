package ru.n5g.birthdays.app.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.n5g.birthdays.app.client.factory.AppClientFactory;
import ru.n5g.birthdays.core.shared.TabEnum;
import ru.n5g.birthdays.note_book.client.activity.ContactListActivity;
import ru.n5g.birthdays.note_book.client.place.ContactListPlace;

public class AppActivityMapper implements ActivityMapper {

  private AppClientFactory factory;


  public AppActivityMapper(AppClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public Activity getActivity(Place place) {
    if(place instanceof ContactListPlace){
      factory.getMainContainer().setHeader(null, factory.getLocalization().contactHeader());
      factory.getMainContainer().selectTab(TabEnum.CONTACT);
      return new ContactListActivity(factory, factory.getContactListFactory());
    }
    return null;
  }
}
