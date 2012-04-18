package ru.n5g.birthdays.core.server.dao;

public interface BaseDao<T> {
  T get(Long id);
}
