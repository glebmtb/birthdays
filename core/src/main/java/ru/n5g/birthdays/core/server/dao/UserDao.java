package ru.n5g.birthdays.core.server.dao;

import ru.n5g.birthdays.core.server.bean.User;

public interface UserDao extends BaseDao<User>{
  
  User loadByUserName(String username);

}
