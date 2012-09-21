package ru.n5g.birthdays.core.server.dao.combo_box;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.n5g.birthdays.core.server.bean.UserRole;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilter;

/**
 * @author belyaev
 */
@Repository
public class UserRoleComboBoxDaoImpl extends AbstractComboBoxDao<UserRole> implements UserRoleComboBoxDao {
  @Override
  public Criteria criteriaSearch(Session session, List<ComboBoxFilter> filters) {
    Criteria criteria = session.createCriteria(UserRole.class);
    for (ComboBoxFilter filter : filters) {
      applyFilterToCriteria(criteria, filter);
    }
    return criteria;
  }
}
