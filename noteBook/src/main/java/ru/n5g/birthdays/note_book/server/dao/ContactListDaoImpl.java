package ru.n5g.birthdays.note_book.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.dao.BaseDaoImpl;

/**
 * @author belyaev
 */
@Repository
public class ContactListDaoImpl extends BaseDaoImpl<Contact> implements ContactListDao {
  @Override
  public List<Contact> loadTableRows() {
    return hibernateTemplate.executeFind(new HibernateCallback<List<Contact>>() {
      @Override
      public List<Contact> doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(Contact.class);
        return criteria.list();
      }
    });
  }

  @Override
  public int getTableRowsCount() {
    return hibernateTemplate.execute(new HibernateCallback<Integer>() {
      @Override
      public Integer doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = session.createCriteria(Contact.class);
        criteria.setProjection(Projections.count("id"));
        return ((Number) criteria.uniqueResult()).intValue();
      }
    });
  }
}
