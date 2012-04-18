package ru.n5g.birthdays.core.server.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

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
}
