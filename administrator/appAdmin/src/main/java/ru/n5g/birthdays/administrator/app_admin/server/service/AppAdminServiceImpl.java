package ru.n5g.birthdays.administrator.app_admin.server.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.administrator.app_admin.client.service.AppAdminService;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.UserDao;

/**
 * @author belyaev
 */
@Service("appAdminService.rpc")
public class AppAdminServiceImpl implements AppAdminService {
  @Autowired
  private UserDao userDao;

  @Override
  public BaseModel getEmployeeName() {
    String userName = userDao.get(User.getAuthenticationUserId()).getUserName();
    if(userName==null)
      userName="";
    BaseModel baseModel = new BaseModel();
    baseModel.set("userName", userName);
    return baseModel;
  }
}
