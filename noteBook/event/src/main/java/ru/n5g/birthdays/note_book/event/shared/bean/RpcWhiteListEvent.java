package ru.n5g.birthdays.note_book.event.shared.bean;

import ru.n5g.birthdays.core.shared.bean.RpcWhiteList;

/**
 * @author belyaev
 */
public class RpcWhiteListEvent extends RpcWhiteList {
  public EventListDTO eventListDTO;

  public RpcWhiteListEvent() {
    eventListDTO = new EventListDTO();
  }
}
