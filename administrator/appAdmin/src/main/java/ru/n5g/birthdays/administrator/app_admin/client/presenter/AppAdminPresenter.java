package ru.n5g.birthdays.administrator.app_admin.client.presenter;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.administrator.app_admin.client.factory.AppAdminClientFactory;
import ru.n5g.birthdays.administrator.app_admin.client.view.AppAdminPage;

public class AppAdminPresenter implements AppAdminPage.Presenter {
  AppAdminClientFactory factory;
  private AppAdminPage view;

  public AppAdminPresenter(AppAdminClientFactory factory) {
    this.factory = factory;
  }

  @Override
  public AppAdminPage start() {
    view = factory.getMainContainer();
    factory.getService().getEmployeeName(new AsyncCallback<BaseModel>() {
      @Override
      public void onFailure(Throwable caught) {
        Info.display("Error", caught.getMessage());
      }

      @Override
      public void onSuccess(BaseModel result) {
        view.setEmployeeName((String) result.get("userName"));
      }
    });
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
  public void openUser() {
//    factory.getPlaceController().goTo(new UserListPlace());
  }
}
