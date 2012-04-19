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
import ru.n5g.birthdays.core.server.bean.Users;


@Repository
public class UserDaoImpl implements UserDao{

  @Autowired
  private HibernateTemplate hibernateTemplate;

  @Override
  public Users loadByUserName(final String username) {
    return hibernateTemplate.execute(new HibernateCallback<Users>() {
      @Override
      public Users doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(Users.class);
        criteria.add(Restrictions.eq("login", username));
        return (Users) criteria.uniqueResult();
      }
    });
  }

  @Override
  public List<Users> loadTableRows() {
    return hibernateTemplate.executeFind(new HibernateCallback<List<Users>>() {
      @Override
      public List<Users> doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(Users.class);
        return criteria.list();
      }
    });
  }

  @Override
  public int getTableRowsCount() {
    return hibernateTemplate.execute(new HibernateCallback<Integer>() {
      @Override
      public Integer doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(Users.class);
        criteria.setProjection(Projections.count("id"));
        return ((Number) criteria.uniqueResult()).intValue();
      }
    });
  }
}
