package ru.n5g.birthdays.core.shared.bean;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author belyaev
 */
public class RpcWhiteList implements IsSerializable {
  public UserRoleCode userRoleCode;
  public UserDTO userDTO;
  public UserRoleDTO userRoleDTO;
  public ContactDTO contactDTO;
  public ActionEnum actionEnum;
  public EventTypeDTO eventTypeDTO;
  public EventDTO eventDTO;

  public RpcWhiteList() {
    this.userRoleCode = UserRoleCode.ROLE_ADMIN;
    this.userDTO = new UserDTO();
    this.userRoleDTO = new UserRoleDTO();
    this.contactDTO = new ContactDTO();
    this.actionEnum = ActionEnum.ADD;
    this.eventTypeDTO = new EventTypeDTO();
    this.eventDTO = new EventDTO();
  }
}
