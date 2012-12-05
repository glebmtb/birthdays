package ru.n5g.birthdays.note_book.app_note_book.client.service;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author belyaev
 */
public interface AppNoteBookServiceAsync {
  void getEmployeeName(AsyncCallback<BaseModel> callback);
}
