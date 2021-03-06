package ru.n5g.birthdays.administrator.user.server.dao;

import java.util.List;

import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.BaseDao;

/**
 * @author belyaev
 */
public interface UserListDao extends BaseDao<User> {
  List loadTableRows();

  int getTableRowsCount();

  boolean isLastAdmin();
}
