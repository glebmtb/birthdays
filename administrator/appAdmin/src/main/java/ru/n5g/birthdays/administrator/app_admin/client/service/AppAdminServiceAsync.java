package ru.n5g.birthdays.administrator.app_admin.client.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author belyaev
 */
public interface AppAdminServiceAsync {
  void getEmployeeName(AsyncCallback<BaseModel> callback);
}
