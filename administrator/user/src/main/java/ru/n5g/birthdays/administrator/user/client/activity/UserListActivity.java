package ru.n5g.birthdays.administrator.user.client.activity;

import com.google.gwt.user.client.Window;
import ru.n5g.birthdays.administrator.user.client.factory.UserListFactory;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ru.n5g.birthdays.core.client.factory.ClientFactory;

/**
 * @author home
 */
public class UserListActivity extends AbstractActivity{
  private ClientFactory clientFactory;
  private UserListFactory factory;


  public UserListActivity(ClientFactory clientFactory, UserListFactory factory) {
    this.clientFactory = clientFactory;
    this.factory = factory;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    Window.setTitle(factory.getLocalization().title());
    panel.setWidget(factory.getPresenter().start());
  }
}
