package ru.n5g.birthdays.core.server.dao;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.UserRole;
import ru.n5g.birthdays.core.shared.bean.UserRoleCode;

/**
 * @author home
 */
@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements UserRoleDao {
  @Override
  public UserRole getRole(final UserRoleCode roleAdmin) {
    return hibernateTemplate.execute(new HibernateCallback<UserRole>() {
      @Override
      public UserRole doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria application = session.createCriteria(UserRole.class);
        application.add(Restrictions.eq("code", roleAdmin));
        return (UserRole) application.uniqueResult();
      }
    });
  }
}
