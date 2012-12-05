package ru.n5g.birthdays.core.server.dao.combo_box;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilter;
import ru.n5g.birthdays.core.shared.data.Filter;

/**
 * @author belyaev
 */
public abstract class AbstractComboBoxDao<T> implements ComboBoxDao<T> {
  @Autowired
  @Qualifier("hibernateTemplate")
  protected HibernateTemplate hibTemplate;

  protected Filter filter;

  public void setHibTemplate(HibernateTemplate hibTemplate) {
    this.hibTemplate = hibTemplate;
  }

  @Override
  public void setFilter(Filter filter) {
    this.filter = filter;
  }

  @Override
  public Integer getRowsCount(List<ComboBoxFilter> filters) {
    GetHibernateCallback callback = new GetHibernateCallback();
    callback.setFilters(filters);
    callback.setIsCount(true);
    Object objCount = hibTemplate.execute(callback);
    int count = 0;
    if (objCount instanceof Long) {
      count = ((Long) objCount).intValue();
    }
    else {
      count = (Integer) objCount;
    }
    return count;
  }

  @Override
  public List<T> loadRows(int fromRow, int rowCount, Style.SortDir sortDir, String orderField, List<ComboBoxFilter> filters) {
    GetHibernateCallback callback = new GetHibernateCallback();
    callback.setIsCount(false);
    callback.setFromRow(fromRow);
    callback.setRowCount(rowCount);
    callback.setSortDir(sortDir);
    callback.setOrderField(orderField);
    callback.setFilters(filters);

    List<T> data = (List<T>) hibTemplate.execute(callback);
    return data;
  }

  private class GetHibernateCallback implements HibernateCallback {
    private boolean isCount = false;
    private Integer fromRow;
    private Integer rowCount;
    private Style.SortDir sortDir;
    private String orderField;
    private List<ComboBoxFilter> filters = new ArrayList<ComboBoxFilter>();

    public void setIsCount(boolean isCount) {
      this.isCount = isCount;
    }

    public void setFromRow(int fromRow) {
      this.fromRow = fromRow;
    }

    public void setRowCount(int rowCount) {
      this.rowCount = rowCount;
    }

    public void setSortDir(Style.SortDir sortDir) {
      this.sortDir = sortDir;
    }

    public void setOrderField(String orderField) {
      this.orderField = orderField;
    }

    public void setFilters(List<ComboBoxFilter> filters) {
      this.filters = filters;
    }


    @Override
    public Object doInHibernate(Session session) throws HibernateException, SQLException {
      Criteria criteria = null;

      criteria = criteriaSearch(session, filters);

      if (isCount) {
        if (criteria != null) {
          criteria.setProjection(Projections.rowCount());
          Number count = (Number) criteria.uniqueResult();
          return count.intValue();
        }
        else {
          return 0;
        }
      }
      else {
        if (criteria != null) {
          switch (sortDir) {
            case ASC:
              addAscOrder(criteria, orderField);
              break;
            case DESC:
              addDescOrder(criteria, orderField);
              break;
            case NONE:
              addNoneOrder(criteria, orderField);
              break;
          }

          if (fromRow != null) {
            criteria.setFirstResult(fromRow);
          }
          if (rowCount != null) {
            criteria.setMaxResults(rowCount);
          }
          return criteria.list();
        }
        else {
          return new ArrayList();
        }
      }
    }
  }

  /**
   * Сортировка по возрастанию
   *
   * @param criteria   критерий
   * @param orderField поле сортировки
   */
  protected void addAscOrder(Criteria criteria, String orderField) {
    criteria.addOrder(Order.asc(orderField));
  }

  /**
   * Сортировка по убыванию
   *
   * @param criteria   критерий
   * @param orderField поле сортировки
   */
  protected void addDescOrder(Criteria criteria, String orderField) {
    criteria.addOrder(Order.desc(orderField));
  }

  /**
   * Дефолтовая сотрировка
   *
   * @param criteria   критерий
   * @param orderField поле сортировки
   */
  protected void addNoneOrder(Criteria criteria, String orderField) {

  }

  protected void applyFilterToCriteria(Criteria criteria, ComboBoxFilter filter) {
    if (filter.getFilterType() != null) {
      switch (filter.getFilterType()) {
        case EQ:
          criteria.add(Restrictions.eq(filter.getField(), filter.getValue()));
          break;
        case NE:
          criteria.add(Restrictions.ne(filter.getField(), filter.getValue()));
          break;
        case LIKE:
          criteria.add(Restrictions.like(filter.getField(), "%".concat(filter.getValue().toString()).concat("%")));
          break;
        case ILIKE:
          criteria.add(Restrictions.ilike(filter.getField(), "%".concat(filter.getValue().toString()).concat("%")));
          break;
        case GT:
          criteria.add(Restrictions.gt(filter.getField(), filter.getValue()));
          break;
        case GE:
          criteria.add(Restrictions.ge(filter.getField(), filter.getValue()));
          break;
        case LT:
          criteria.add(Restrictions.lt(filter.getField(), filter.getValue()));
          break;
        case LE:
          criteria.add(Restrictions.le(filter.getField(), filter.getValue()));
          break;
        case IN:
          criteria.add(Restrictions.in(filter.getField(), (ArrayList) filter.getValue()));
          break;
        case NOT_IN:
          criteria.add(Restrictions.not(Restrictions.in(filter.getField(), (ArrayList) filter.getValue())));
          break;
        case ISNULL:
          criteria.add(Restrictions.isNull(filter.getField()));
          break;
      }
    }
  }

  public abstract Criteria criteriaSearch(Session session, List<ComboBoxFilter> filters);

  public Criteria criteriaSearch(Session session, List<ComboBoxFilter> filters, Filter filter) {
    return criteriaSearch(session, filters);
  }

}
