package ru.n5g.birthdays.administrator.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UserDTO;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

public interface AdministratorServiceAsync {
  void loadUserList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<UserDTO>> callback);

  void setUsers(UserDTO dto, AsyncCallback<Void> asyncCallback);

  void delUsers(UserDTO dto, AsyncCallback<Void> asyncCallback);

  void loadUserRoleList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<UserRoleDTO>> callback);

  void registerClasses(RpcWhiteList whiteList, AsyncCallback<RpcWhiteList> async);
}
