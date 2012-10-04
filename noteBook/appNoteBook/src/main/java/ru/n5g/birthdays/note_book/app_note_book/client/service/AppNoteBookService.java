package ru.n5g.birthdays.note_book.app_note_book.client.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author belyaev
 */
@RemoteServiceRelativePath("appService.rpc")
public interface AppNoteBookService extends RemoteService {

  BaseModel getEmployeeName();
}
