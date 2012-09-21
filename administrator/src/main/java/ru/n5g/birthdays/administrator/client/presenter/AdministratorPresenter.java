package ru.n5g.birthdays.administrator.client.presenter;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.administrator.client.factory.AdministratorFactory;
import ru.n5g.birthdays.administrator.client.view.AdministratorView;
import ru.n5g.birthdays.administrator.client.view.AdministratorViewImpl;
import ru.n5g.birthdays.administrator.client.view.EditUserWindow;
import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;
import ru.n5g.birthdays.core.shared.bean.UsersDTO;

public class AdministratorPresenter extends SimpleWindowPresenter {
  private AdministratorView view;
  private AdministratorFactory factory;

  public AdministratorPresenter(AdministratorFactory factory) {
    super(factory.getLocalization());
    this.factory = factory;
  }

  public AdministratorView start() {
    if (view == null)
      view = new AdministratorViewImpl(this, factory.getLocalization());
    return view;
  }

  public ListStore<UsersDTO> loadUserList() {
    RpcProxy<BasePagingLoadResult<UsersDTO>> proxy = new RpcProxy<BasePagingLoadResult<UsersDTO>>() {
      @Override
      protected void load(Object loadConfig, AsyncCallback<BasePagingLoadResult<UsersDTO>> listAsyncCallback) {
        factory.getService().loadUserList((BasePagingLoadConfig) loadConfig, listAsyncCallback);
      }
    };
    PagingLoader<PagingLoadResult<UsersDTO>> loader;
    loader = new BasePagingLoader<PagingLoadResult<UsersDTO>>(proxy, new ModelReader());

    return new ListStore<UsersDTO>(loader);
  }

  public void addUser() {
    EditUserWindow window = new EditUserWindow(this, null);
    window.show();
  }

  public void editUser(UsersDTO model) {
    EditUserWindow window = new EditUserWindow(this, model);
    window.show();
  }

  public void saveEditUserWindow(UsersDTO dto, final EditUserWindow window) {
    factory.getService().setUsers(dto, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        Info.display("Error", caught.getMessage());
      }

      public void onSuccess(Void result) {
        window.hide();
        view.refresh();
        Info.display(factory.getLocalization().information(), factory.getLocalization().saveSuccess());
      }
    });
  }

  public void delUser(UsersDTO dto) {
    factory.getService().delUsers(dto, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        Info.display("Error", caught.getMessage());
      }

      public void onSuccess(Void result) {
        view.refresh();
        Info.display(factory.getLocalization().information(), factory.getLocalization().delSuccess());
      }
    });
  }

  public ListStore<UserRoleDTO> getUserRoleComboStore() {
    RpcProxy<BasePagingLoadResult<UserRoleDTO>> proxy = new RpcProxy<BasePagingLoadResult<UserRoleDTO>>() {
      @Override
      protected void load(Object loadConfig, AsyncCallback<BasePagingLoadResult<UserRoleDTO>> callback) {
        factory.getService().loadUserRoleList((BasePagingLoadConfig) loadConfig, callback);
      }
    };
    PagingLoader<PagingLoadResult<UserRoleDTO>> loader = new BasePagingLoader<PagingLoadResult<UserRoleDTO>>(proxy, new ModelReader());
    loader.setSortDir(Style.SortDir.ASC);
    loader.setSortField(UserRoleDTO.NAME);
    loader.setRemoteSort(true);
    return new ListStore<UserRoleDTO>(loader);
  }
}
