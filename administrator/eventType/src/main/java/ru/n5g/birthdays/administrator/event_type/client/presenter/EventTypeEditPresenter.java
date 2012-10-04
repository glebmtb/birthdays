package ru.n5g.birthdays.administrator.event_type.client.presenter;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.n5g.birthdays.administrator.event_type.client.factory.EventTypeEditFactory;
import ru.n5g.birthdays.administrator.event_type.client.view.EventTypeEditWindow;
import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.components.client.view.SimpleWindowView;
import ru.n5g.birthdays.core.shared.bean.ActionEnum;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author belyaev
 */
public class EventTypeEditPresenter extends SimpleWindowPresenter {
  private EventTypeEditFactory factory;
  private View window;
  private IsSave isSave;

  public EventTypeEditPresenter(EventTypeEditFactory factory) {
    super(factory.getLocalization());
    this.factory = factory;
  }

  public void addContact(IsSave isSave) {
    this.isSave = isSave;
    openWindow(ActionEnum.ADD, new EventTypeDTO());
  }

  public void editContact(EventTypeDTO dto, IsSave isSave) {
    this.isSave = isSave;
    openWindow(ActionEnum.EDIT, dto);
  }

  private void openWindow(ActionEnum action, EventTypeDTO dto) {
    if (action == null || dto == null) {
      Info.display(factory.getLocalization().titleError(), factory.getLocalization().emptyContact());
    }
    window = new EventTypeEditWindow(this, factory.getLocalization(), action, dto);
    window.show();
  }

  public void save(EventTypeDTO dto) {
    factory.getService().saveContact(dto, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        Info.display("Error", caught.getMessage());
      }

      public void onSuccess(Void result) {
        window.hide();
        Info.display(factory.getLocalization().information(), factory.getLocalization().saveSuccess());
        isSave.onSuccess();
      }
    });
  }

  public interface View extends SimpleWindowView {
    void hide();
  }

  public interface IsSave {
    void onSuccess();
  }
}
