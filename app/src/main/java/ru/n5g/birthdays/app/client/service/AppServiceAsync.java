package ru.n5g.birthdays.app.client.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author belyaev
 */
public interface AppServiceAsync {
  void getEmployeeName(AsyncCallback<BaseModel> callback);
}
