package ru.n5g.birthdays.core.server.dao;

public interface BaseDAO<T> {
  T get(Long id);
}
