package ru.n5g.birthdays.administrator.user.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UserDTO;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

@RemoteServiceRelativePath("userListService.rpc")
public interface UserListService extends RemoteService  {
  BasePagingLoadResult<UserDTO> loadUserList(BasePagingLoadConfig loadConfig);

  void setUsers(UserDTO dto);

  void delUsers(UserDTO dto);

  BasePagingLoadResult<UserRoleDTO> loadUserRoleList(BasePagingLoadConfig loadConfig);

  RpcWhiteList registerClasses(RpcWhiteList whiteList);
}
