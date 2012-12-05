package ru.n5g.birthdays.note_book.event.server.dao;

import java.sql.SQLException;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.FilterConfig;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.Event;
import ru.n5g.birthdays.core.server.bean.EventListView;
import ru.n5g.birthdays.core.server.dao.BaseDaoImpl;
import ru.n5g.birthdays.note_book.event.shared.bean.EventListDTO;

/**
 * @author belyaev
 */
@Repository
public class EventListDaoImpl extends BaseDaoImpl<EventListView> implements EventListDao {
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

  private void addOrder(Criteria criteria, Object sortDirO, Object sortFieldO) {
    if (sortDirO == null || sortFieldO == null)
      return;

    Style.SortDir sortDir = (Style.SortDir) sortDirO;
    String sortField = (String) sortFieldO;

    if (EventListDTO.EVENT_DAYS_LEFT.equals(sortField)) {
      switch (sortDir) {
        case ASC:
          criteria.addOrder(Order.asc("eventDaysLeft"));
          break;
        case DESC:
          criteria.addOrder(Order.desc("eventDaysLeft"));
          break;
      }
    }
    else if (EventListDTO.EVENT_TYPE_NAME.equals(sortField)) {
      switch (sortDir) {
        case ASC:
          criteria.addOrder(Order.asc("eventTypeName"));
          break;
        case DESC:
          criteria.addOrder(Order.desc("eventTypeName"));
          break;
      }
    }
    else if (EventListDTO.EVENT_DAY.equals(sortField)) {
      switch (sortDir) {
        case ASC:
          criteria.addOrder(Order.asc("eventMonth"));
          criteria.addOrder(Order.asc("eventDay"));
          break;
        case DESC:
          criteria.addOrder(Order.desc("eventMonth"));
          criteria.addOrder(Order.desc("eventDay"));
          break;
      }
    }
    else if (EventListDTO.EVENT_YEARS.equals(sortField)) {
      switch (sortDir) {
        case ASC:
          criteria.addOrder(Order.asc("eventYears"));
          break;
        case DESC:
          criteria.addOrder(Order.desc("eventYears"));
          break;
      }
    }
    else if (EventListDTO.CONTACT_FIO.equals(sortField)) {
      switch (sortDir) {
        case ASC:
          criteria.addOrder(Order.asc("contactLastName"));
          criteria.addOrder(Order.asc("contactFirstName"));
          criteria.addOrder(Order.asc("contactMiddleName"));
          criteria.addOrder(Order.asc("contactNickname"));
          break;
        case DESC:
          criteria.addOrder(Order.desc("contactLastName"));
          criteria.addOrder(Order.desc("contactFirstName"));
          criteria.addOrder(Order.desc("contactMiddleName"));
          criteria.addOrder(Order.desc("contactNickname"));
          break;
      }
    }
  }

  @Override
  public List loadTableRows(final BaseModelData filter) {
    return hibernateTemplate.executeFind(new HibernateCallback<List<Event>>() {
      @Override
      public List<Event> doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = createCriteria(session, filter);
        addOrder(criteria, filter.get("sortDir"), filter.get("sortField"));
        addFilters(criteria, filter.get("filterConfigs"));
        return criteria.list();
      }
    });
  }

  private Criteria createCriteria(Session session, BaseModelData filter) {
    Criteria criteria = session.createCriteria(EventListView.class);
    criteria.add(Restrictions.eq("userId", filter.get("userId")));
    return criteria;
  }

  private void addFilters(Criteria criteria, Object filterConfigs) {
    if (filterConfigs == null)
      return;
    List<FilterConfig> filterConfigList = (List<FilterConfig>) filterConfigs;
    for (FilterConfig fc : filterConfigList) {
      if (fc.getField().equals("storeFilterField") && fc.getType() instanceof String) {
        Disjunction dis = Restrictions.disjunction();
        dis.add(Restrictions.ilike("contactFio", (String) fc.getValue(), MatchMode.ANYWHERE));
        criteria.add(dis);
      }
    }
  }
}
