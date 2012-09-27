package ru.n5g.birthdays.administrator.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.administrator.shared.bean.AdministratorListDTO;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

@RemoteServiceRelativePath("administratorService.rpc")
public interface AdministratorService<M extends AdministratorListDTO> extends RemoteService  {
  BasePagingLoadResult<M> loadUserList(BasePagingLoadConfig loadConfig);

  void setUsers(M dto);

  void delUsers(M dto);

  BasePagingLoadResult<UserRoleDTO> loadUserRoleList(BasePagingLoadConfig loadConfig);

  RpcWhiteList registerClasses(RpcWhiteList whiteList);
}
