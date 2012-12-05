package ru.n5g.birthdays.core.shared.combo_box;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Типы фильтров
 *
 * @author belyaev
 */
public enum ComboBoxFilterType implements IsSerializable {
  /**
   * EQUALS, полное совпадение
   */
  EQ,
  /**
   * LIKE, содержание
   */
  LIKE,
  /**
   * ILIKE, регистронезависимое содержание
   */
  ILIKE,
  /**
   * GREATER THAN, больше чем
   */
  GT,
  /**
   * GREATER OR EQUALS, больше чем или равно
   */
  GE,
  /**
   * LESS THAN, меньше чем
   */
  LT,
  /**
   * LESS THAN OR EQUALS, меньше чем или равно
   */
  LE,
  /**
   * NOT EQUALS, не равно
   */
  NE,
  /**
   * IN, включает
   */
  IN,
  /**
   * NOT_IN, не включает
   */
  NOT_IN,
  /**
   * IS NULL, если NULL
   */
  ISNULL
}
