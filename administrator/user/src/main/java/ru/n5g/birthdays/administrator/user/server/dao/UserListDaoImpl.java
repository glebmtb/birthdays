package ru.n5g.birthdays.administrator.user.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.server.dao.BaseDaoImpl;
import ru.n5g.birthdays.core.shared.bean.UserRoleCode;

/**
 * @author belyaev
 */
@Repository
public class UserListDaoImpl extends BaseDaoImpl<User> implements UserListDao {

  @Override
  public List<User> loadTableRows() {
    return hibernateTemplate.executeFind(new HibernateCallback<List<User>>() {
      @Override
      public List<User> doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(User.class);
        return criteria.list();
      }
    });
  }

  @Override
  public int getTableRowsCount() {
    return hibernateTemplate.execute(new HibernateCallback<Integer>() {
      @Override
      public Integer doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(Projections.count("id"));
        return ((Number) criteria.uniqueResult()).intValue();
      }
    });
  }

  @Override
  public boolean isLastAdmin() {
    return hibernateTemplate.execute(new HibernateCallback<Boolean>() {
      @Override
      public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(User.class);
        criteria.createCriteria("role", "role");
        criteria.add(Restrictions.eq("role.code", UserRoleCode.ROLE_ADMIN));
        criteria.setProjection(Projections.rowCount());
        Number count = (Number) criteria.uniqueResult();
        return count.intValue() == 1;
      }
    });
  }
}
