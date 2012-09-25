package ru.n5g.birthdays.note_book.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ru.n5g.birthdays.core.client.factory.ClientFactory;
import ru.n5g.birthdays.note_book.client.factory.ContactListFactory;

public class ContactListActivity extends AbstractActivity {
  private ClientFactory clientFactory;
  private ContactListFactory factory;

  public ContactListActivity(ClientFactory clientFactory, ContactListFactory factory) {
    this.clientFactory = clientFactory;
    this.factory = factory;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    Window.setTitle(factory.getListLocalization().title());
    panel.setWidget(factory.getPresenter().start());
  }
}
