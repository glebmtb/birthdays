package ru.n5g.birthdays.note_book.app_note_book.client.presenter;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import ru.n5g.birthdays.note_book.app_note_book.client.factory.AppNoteBookClientFactory;
import ru.n5g.birthdays.note_book.app_note_book.client.view.AppNoteBookPage;
import ru.n5g.birthdays.note_book.contact.client.place.ContactListPlace;
import ru.n5g.birthdays.note_book.event.client.place.EventListPlace;

public class AppNoteBookPresenter implements AppNoteBookPage.Presenter {
  AppNoteBookClientFactory factory;
  private AppNoteBookPage view;

  public AppNoteBookPresenter(AppNoteBookClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public AppNoteBookPage start() {
    view = factory.getMainContainer();
    view.setPresenter(this);
    return view;
  }

  @Override
  public void checkPermissions(String permissionCode, TabPanel toolBar, TabItem tabItem) {
    //TODO: implement this method
  }

  @Override
  public void checkPermissions(String permissionCode, Component component, boolean hideIfDenied) {
    //TODO: implement this method
  }

  @Override
  public void openContact() {
    factory.getPlaceController().goTo(new ContactListPlace());
  }

  @Override
  public void openEventType() {
    factory.getPlaceController().goTo(new EventListPlace());
  }
}
