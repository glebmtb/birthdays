package ru.n5g.birthdays.administrator.client.presenter;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.administrator.client.factory.AdministratorFactory;
import ru.n5g.birthdays.administrator.client.view.AdministratorView;
import ru.n5g.birthdays.administrator.client.view.AdministratorViewImpl;
import ru.n5g.birthdays.core.shared.bean.UsersDTO;

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
}
