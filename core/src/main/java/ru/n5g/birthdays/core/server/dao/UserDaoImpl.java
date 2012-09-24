package ru.n5g.birthdays.core.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.User;
import ru.n5g.birthdays.core.shared.bean.UserRoleCode;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

  @Autowired
  private HibernateTemplate hibernateTemplate;

  @Override
  public User loadByUserName(final String username) {
    return hibernateTemplate.execute(new HibernateCallback<User>() {
      @Override
      public User doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", username));
        return (User) criteria.uniqueResult();
      }
    });
  }

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
