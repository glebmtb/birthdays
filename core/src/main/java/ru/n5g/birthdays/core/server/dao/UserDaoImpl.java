package ru.n5g.birthdays.core.server.dao;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.User;


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
}
