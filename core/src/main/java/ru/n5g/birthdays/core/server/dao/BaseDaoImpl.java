package ru.n5g.birthdays.core.server.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BaseDaoImpl<T> implements BaseDao<T> {
  private Class<T> beanClass;
  @Autowired
  protected HibernateTemplate hibernateTemplate = null;

  @Override
  public T get(Long id) {
    return hibernateTemplate.get(getBeanClass(), id);
  }

  protected final Class<T> getBeanClass() {
    if (beanClass == null) {
      ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
      Type genericType = thisType.getActualTypeArguments()[0];

      if (genericType instanceof Class) {
        beanClass = (Class<T>) genericType;
      }
      else {
        beanClass = (Class<T>) ((TypeVariable) genericType).getBounds()[0];
      }
    }

    return beanClass;
  }

  @Override
  public void saveOrUpdateNonTransactional(T bean) {
    hibernateTemplate.saveOrUpdate(bean);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  public void saveOrUpdate(T bean) {
    saveOrUpdateNonTransactional(bean);
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  public void delete(T bean) {
    deleteNonTransactional(bean);
  }

  @Override
  public void deleteNonTransactional(T bean) {
    hibernateTemplate.delete(bean);
  }
}
