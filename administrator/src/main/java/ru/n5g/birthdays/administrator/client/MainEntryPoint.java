package ru.n5g.birthdays.administrator.client;

import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import ru.n5g.birthdays.administrator.client.factory.AdministratorFactory;

public class MainEntryPoint implements com.google.gwt.core.client.EntryPoint {

  public void onModuleLoad() {
    AdministratorFactory clientFactory = GWT.create(AdministratorFactory.class);

    Viewport v = new Viewport();
    v.setLayout(new FitLayout());
    v.add(clientFactory.getPresenter().start().asWidget());

    RootPanel.get().add(v);
  }
}
