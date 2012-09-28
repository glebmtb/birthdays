package ru.n5g.birthdays.app.server.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.n5g.birthdays.app.client.service.AppService;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.UserDao;

/**
 * @author belyaev
 */
@Service("appService.rpc")
public class AppServiceImpl implements AppService {
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
