package ru.n5g.birthdays.app.client.presenter;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import ru.n5g.birthdays.app.client.factory.AppClientFactory;
import ru.n5g.birthdays.app.client.view.AppPage;

public class AppPresenter implements AppPage.Presenter {
  AppClientFactory factory;
  private AppPage view;

  public AppPresenter(AppClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public AppPage start() {
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
  public void openAdministrator() {
    //TODO: implement this method
  }
}
