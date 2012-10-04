package ru.n5g.birthdays.administrator.event_type.server.dao;

import java.sql.SQLException;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.EventType;
import ru.n5g.birthdays.core.server.dao.BaseDaoImpl;

/**
 * @author belyaev
 */
@Repository
public class EventTypeListDaoImpl extends BaseDaoImpl<EventType> implements EventTypeListDao {
  @Override
  public List loadTableRows(final BaseModelData filter) {
    return hibernateTemplate.executeFind(new HibernateCallback<List<EventType>>() {
      @Override
      public List<EventType> doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria criteria = createCriteria(session, filter);
        return criteria.list();
      }
    });
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

  private Criteria createCriteria(Session session, BaseModelData filter){
    Criteria criteria = session.createCriteria(EventType.class);
    return criteria;
  }
}
