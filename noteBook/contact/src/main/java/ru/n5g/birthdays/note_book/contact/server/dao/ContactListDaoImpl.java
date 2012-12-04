package ru.n5g.birthdays.note_book.contact.server.dao;

import java.sql.SQLException;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModelData;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.Contact;
import ru.n5g.birthdays.core.server.dao.BaseDaoImpl;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactListDTO;

/**
 * @author belyaev
 */
@Repository
public class ContactListDaoImpl extends BaseDaoImpl<Contact> implements ContactListDao {
  @Override
  public List loadTableRows(final BaseModelData filter) {
    return hibernateTemplate.executeFind(new HibernateCallback<List<Contact>>() {
      @Override
      public List<Contact> doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = createCriteria(session, filter);     //SortDir
        addOrder(criteria, filter.get("sortDir"), filter.get("sortField"));
        return criteria.list();
      }
    });
  }

  private void addOrder(Criteria criteria, Object sortDirO, Object sortFieldO) {
    if (sortDirO == null || sortFieldO == null)
      return;

    Style.SortDir sortDir = (Style.SortDir) sortDirO;
    String sortField = (String) sortFieldO;

    if (ContactListDTO.FIO.equals(sortField)) {
      switch (sortDir) {
        case ASC:
          criteria.addOrder(Order.asc("lastName"));
          criteria.addOrder(Order.asc("firstName"));
          criteria.addOrder(Order.asc("middleName"));
          break;
        case DESC:
          criteria.addOrder(Order.desc("lastName"));
          criteria.addOrder(Order.desc("firstName"));
          criteria.addOrder(Order.desc("middleName"));
          break;
      }
    }
    else if (ContactListDTO.NICKNAME.equals(sortField)) {
      switch (sortDir) {
        case ASC:
          criteria.addOrder(Order.asc("nickname"));
          break;
        case DESC:
          criteria.addOrder(Order.desc("nickname"));
          break;
      }
    }
  }

  @Override
  public int getTableRowsCount(final BaseModelData filter) {
    return hibernateTemplate.execute(new HibernateCallback<Integer>() {
      @Override
      public Integer doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = createCriteria(session, filter);
        criteria.setProjection(Projections.count("id"));
        return ((Number) criteria.uniqueResult()).intValue();
      }
    });
  }

  private Criteria createCriteria(Session session, BaseModelData filter) {
    Criteria criteria = session.createCriteria(Contact.class);
    criteria.add(Restrictions.eq("userId", filter.get("userId")));
    return criteria;
  }
}
