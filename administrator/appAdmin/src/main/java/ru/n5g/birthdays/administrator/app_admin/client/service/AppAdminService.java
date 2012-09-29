package ru.n5g.birthdays.administrator.app_admin.client.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author belyaev
 */
@RemoteServiceRelativePath("appAdminService.rpc")
public interface AppAdminService extends RemoteService {

  BaseModel getEmployeeName();
}
