package ru.n5g.birthdays.administrator.user.client.activity;

import ru.n5g.birthdays.administrator.user.client.factory.UserListFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ru.n5g.birthdays.core.client.factory.ClientFactory;

/**
 * @author home
 */
public class AppAdminActivity extends AbstractActivity{
  private ClientFactory clientFactory;
  private UserListFactory factory;

  public AppAdminActivity(ClientFactory clientFactory, UserListFactory factory) {
    this.clientFactory = clientFactory;
    this.factory = factory;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    //TODO: implement this method
  }
}
