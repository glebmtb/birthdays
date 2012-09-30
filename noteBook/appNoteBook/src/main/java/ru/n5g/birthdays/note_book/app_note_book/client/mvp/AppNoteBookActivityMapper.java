package ru.n5g.birthdays.note_book.app_note_book.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.n5g.birthdays.note_book.app_note_book.client.factory.AppNoteBookClientFactory;
import ru.n5g.birthdays.core.shared.TabEnum;
import ru.n5g.birthdays.note_book.contact.client.activity.ContactListActivity;
import ru.n5g.birthdays.note_book.contact.client.place.ContactListPlace;

public class AppNoteBookActivityMapper implements ActivityMapper {

  private AppNoteBookClientFactory factory;


  public AppNoteBookActivityMapper(AppNoteBookClientFactory factory) {
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
