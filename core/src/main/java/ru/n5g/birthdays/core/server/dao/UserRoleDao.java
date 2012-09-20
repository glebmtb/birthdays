package ru.n5g.birthdays.core.server.dao;

import ru.n5g.birthdays.core.server.bean.UserRole;
import ru.n5g.birthdays.core.shared.bean.UserRoleCode;

/**
 * @author home
 */
public interface UserRoleDao extends BaseDao<UserRole> {

  UserRole getRole(UserRoleCode roleAdmin);
}
