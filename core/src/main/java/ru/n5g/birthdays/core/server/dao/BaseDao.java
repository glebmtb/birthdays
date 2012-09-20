package ru.n5g.birthdays.core.server.dao;

public interface BaseDao<T> {

  /**
   * Сохранить объект bean в базе данных
   */
  void saveOrUpdate(T bean);

  /**
   * Сохранить объект bean в базе данных используя существующую транзакцию
   */
  void saveOrUpdateNonTransactional(T bean);

  /**
   * Извлечь объект, предварительно сохраненный в базе данных, используя указанный id в качестве первичного ключа
   */
  T get(Long id);

  /**
   * Удалить объект из базы данных
   */
  void delete(T bean);

  /**
   * Удалить объект из базы данных используя существующую транзакцию
   */
  void deleteNonTransactional(T bean);
}
