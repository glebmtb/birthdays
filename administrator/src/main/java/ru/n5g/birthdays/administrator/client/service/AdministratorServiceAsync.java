package ru.n5g.birthdays.administrator.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.administrator.shared.bean.AdministratorListDTO;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

public interface AdministratorServiceAsync<M extends AdministratorListDTO> {
  void loadUserList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<M>> callback);

  void setUsers(M dto, AsyncCallback<Void> asyncCallback);

  void delUsers(M dto, AsyncCallback<Void> asyncCallback);

  void loadUserRoleList(BasePagingLoadConfig loadConfig, AsyncCallback<BasePagingLoadResult<UserRoleDTO>> callback);

  void registerClasses(RpcWhiteList whiteList, AsyncCallback<RpcWhiteList> async);
}
