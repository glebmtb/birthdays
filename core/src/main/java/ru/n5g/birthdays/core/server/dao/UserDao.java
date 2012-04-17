package ru.n5g.birthdays.core.server.dao;


import ru.n5g.birthdays.core.server.bean.Users;

public interface UserDao {
  
  Users loadByUserName(String username);
}
