package ru.n5g.birthdays.core.shared;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.google.gwt.user.client.rpc.IsSerializable;


public class RpcWhiteList implements IsSerializable {
  public BasePagingLoadConfig pagingLoadConfig;

  public RpcWhiteList() {
    pagingLoadConfig = new BasePagingLoadConfig();
  }
}
