package ru.n5g.birthdays.core.shared.bean;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author belyaev
 */
public class RpcWhiteList implements IsSerializable{
  public UserRoleCode userRoleCode;

  public RpcWhiteList() {
    this.userRoleCode = UserRoleCode.ROLE_ADMIN;
  }
}
