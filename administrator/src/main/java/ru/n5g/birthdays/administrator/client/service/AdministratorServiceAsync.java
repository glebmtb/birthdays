package ru.n5g.birthdays.administrator.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.UsersDTO;

public interface AdministratorServiceAsync {
  void loadUserList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<UsersDTO>> callback);

  void setUsers(UsersDTO dto, AsyncCallback<Void> asyncCallback);

  void delUsers(UsersDTO dto, AsyncCallback<Void> asyncCallback);
}
