package ru.n5g.birthdays.administrator.event_type.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import ru.n5g.birthdays.administrator.event_type.client.factory.EventTypeListFactory;
import ru.n5g.birthdays.core.client.factory.ClientFactory;

public class EventTypeListActivity extends AbstractActivity {
  private ClientFactory clientFactory;
  private EventTypeListFactory factory;

  public EventTypeListActivity(ClientFactory clientFactory, EventTypeListFactory factory) {
    this.clientFactory = clientFactory;
    this.factory = factory;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    Window.setTitle(factory.getLocalization().title());
    panel.setWidget(factory.getPresenter().start());
  }
}
