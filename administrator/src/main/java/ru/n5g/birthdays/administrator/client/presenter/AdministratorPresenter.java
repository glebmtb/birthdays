package ru.n5g.birthdays.administrator.client.presenter;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.administrator.client.factory.AdministratorFactory;
import ru.n5g.birthdays.administrator.client.view.AdministratorView;
import ru.n5g.birthdays.administrator.client.view.AdministratorViewImpl;

public class AdministratorPresenter {
  private AdministratorView view;
  private AdministratorFactory factory;

  public AdministratorPresenter(AdministratorFactory factory) {
    this.factory = factory;
  }

  public AdministratorView start() {
    if (view == null)
      view = new AdministratorViewImpl(this, factory.getLocalization());
    return view;
  }

  public void getMessage(){
    factory.getService().getMessage(new AsyncCallback<String>() {
      public void onFailure(Throwable caught) {
        Info.display("service", "error");
      }

      public void onSuccess(String result) {
        Info.display("service", result);
      }
    });
  }
}
