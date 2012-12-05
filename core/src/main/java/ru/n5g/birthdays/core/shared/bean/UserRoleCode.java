package ru.n5g.birthdays.core.shared.bean;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author home
 */
public enum UserRoleCode implements Serializable, IsSerializable {
  /**
   * Администратор
   */
  ROLE_ADMIN,

  /**
   * Пользователь
   */
  ROLE_USER
}
