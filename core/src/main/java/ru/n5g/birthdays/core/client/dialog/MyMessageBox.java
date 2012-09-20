package ru.n5g.birthdays.core.client.dialog;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;

/**
 * @author belyaev
 */
public class MyMessageBox extends MessageBox {
  /**
   * Сообщение об ошибке
   *
   * @param title    the title bar text
   * @param msg      the message box body text
   * @param callback listener to be called when the box is closed
   * @return the new message box instance
   */
  public static MessageBox error(String title, String msg, Listener<MessageBoxEvent> callback) {
    MessageBox box = new MessageBox();
    box.setTitle(title);
    box.setMessage(msg);
    box.setButtons(OK);
    box.setIcon(ERROR);
    Dialog dialog = box.getDialog();
    if (callback != null) {
      dialog.addListener(Events.Hide, callback);
    }
    dialog.show();
    return box;
  }

  /**
   * Диалог подтверждения
   *
   * @param title
   * @param msg
   * @param callback
   * @return
   */
  public static MessageBox cancelableConfirm(String title, String msg, Listener<MessageBoxEvent> callback) {
    MessageBox box = new MessageBox();
    box.setTitle(title);
    box.setMessage(msg);
    box.setIcon(QUESTION);
    box.setButtons(YESNOCANCEL);
    Dialog dialog = box.getDialog();
    if (callback != null) {
      dialog.addListener(Events.Hide, callback);
    }
    dialog.show();
    return box;
  }
}
