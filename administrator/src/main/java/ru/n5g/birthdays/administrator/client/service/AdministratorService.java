package ru.n5g.birthdays.administrator.client.service;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UsersDTO;

@RemoteServiceRelativePath("administratorService.rpc")
public interface AdministratorService extends RemoteService {
  BasePagingLoadResult<UsersDTO> loadUserList(BasePagingLoadConfig loadConfig);

  RpcWhiteList registerClasses(RpcWhiteList whiteList) throws Exception, RuntimeException;
}
