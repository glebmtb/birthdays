package ru.n5g.birthdays.core.shared.combo_box;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Фильтр для комбобокса
 *
 * @author belyaev
 */
public class ComboBoxFilter implements IsSerializable {
  private String field;
  private Serializable value;
  private ComboBoxFilterType filterType;

  public ComboBoxFilter() {
  }

  /**
   * Создать фильтр для комбобокса.
   * Тим фильтра - полное совпадение.
   *
   * @param field наименование поля
   * @param value значение поля
   */
  public ComboBoxFilter(String field, Serializable value) {
    this(field, value, ComboBoxFilterType.EQ);
  }

  /**
   * Создать фильтр для комбобокса
   *
   * @param field      наименование поля
   * @param value      значение поля
   * @param filterType тип фильтра
   */
  public ComboBoxFilter(String field, Serializable value, ComboBoxFilterType filterType) {
    this.field = field;
    this.value = value;
    this.filterType = filterType;
  }

  /**
   * Создать фильтр для комбобокса
   *
   * @param field      наименование поля
   * @param value      значение поля
   * @param filterType тип фильтра
   * @deprecated Добавлен для совместимости с теми местами, где передаётся Object. Значения должны быть сериализуемыми.
   */
  @Deprecated
  public ComboBoxFilter(String field, Object value, ComboBoxFilterType filterType) {
    this(field, (Serializable) value, filterType);
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  @SuppressWarnings("unchecked")
  public <X extends Serializable> X getValue() {
    return (X) value;
  }

  /**
   * Задать значение
   *
   * @param value значение
   */
  public void setValue(Serializable value) {
    this.value = value;
  }

  /**
   * Задать значение
   *
   * @param value значение
   * @deprecated оставлен для совместимости с теми местами, где передаётся Object. Значения должны быть сериализуемыми.
   */
  @Deprecated
  public void setValue(Object value) {
    setValue((Serializable) value);
  }

  public ComboBoxFilterType getFilterType() {
    return filterType;
  }

  public void setFilterType(ComboBoxFilterType filterType) {
    this.filterType = filterType;
  }

}
