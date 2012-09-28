package ru.n5g.birthdays.app.client.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author belyaev
 */
@RemoteServiceRelativePath("appService.rpc")
public interface AppService extends RemoteService {

  BaseModel getEmployeeName();
}
