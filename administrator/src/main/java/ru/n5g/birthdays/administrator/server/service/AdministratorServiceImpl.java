package ru.n5g.birthdays.administrator.server.service;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.administrator.client.service.AdministratorService;
import ru.n5g.birthdays.core.server.bean.Users;
import ru.n5g.birthdays.core.server.dao.UserDao;
import ru.n5g.birthdays.core.shared.RpcWhiteList;
import ru.n5g.birthdays.core.shared.bean.UsersDTO;

@Service
public class AdministratorServiceImpl implements AdministratorService {
  @Autowired
  private UserDao userDao;

  @Override
  public BasePagingLoadResult<UsersDTO> loadUserList(BasePagingLoadConfig loadConfig) {
    List<UsersDTO> agentModelList = getModelList(userDao.loadTableRows());
    int start = 0;
    int limit = userDao.getTableRowsCount();
    int offsetLimit = 0;

    BasePagingLoadResult<UsersDTO> basePagingLoadResult;
    basePagingLoadResult = new BasePagingLoadResult<UsersDTO>(agentModelList, start, limit);
    return basePagingLoadResult;
  }

  @Override
  public RpcWhiteList registerClasses(RpcWhiteList whiteList) throws Exception, AccessDeniedException, RuntimeException {
    return new RpcWhiteList();
  }

  protected List<UsersDTO> getModelList(List dataList) {
    List resultList = new ArrayList();
    for (Object o : dataList) {
      UsersDTO dto = new UsersDTO();
      Users bean = (Users)o;
      dto.setId(bean.getId());
      dto.setLogin(bean.getLogin());
      resultList.add(dto);
    }
    return resultList;
  }
}
