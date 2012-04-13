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
import ru.n5g.birthdays.core.server.bean.People;

@Repository
public class PeopleDAOImpl implements PeopleDAO {

  @Autowired
  protected HibernateTemplate hibernateTemplate;

  @Override
  public People get(final Long id) {

    return hibernateTemplate.execute(new HibernateCallback<People>() {
      @Override
      public People doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(People.class);
        criteria.add(Restrictions.eq("id", id));
        return (People) criteria.uniqueResult();
      }
    });
//    People people = new People();
//    people.setId(surveyId);
//    people.setFirstName("Hello World DAO");
//    return people;
  }
}
