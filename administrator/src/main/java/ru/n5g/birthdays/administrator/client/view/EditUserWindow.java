package ru.n5g.birthdays.administrator.client.view;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import ru.n5g.birthdays.administrator.client.localization.AdministratorLocalization;
import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.components.client.view.SimpleWindowView;

/**
 * @author belyaev
 */
public class EditUserWindow extends SimpleWindowView {
  private AdministratorLocalization localization;

  public EditUserWindow(SimpleWindowPresenter presenter) {
    super(presenter);
    this.localization = (AdministratorLocalization) presenter.getLocalization();
  }

  @Override
  protected String getTitleWindow() {
    return localization.titleEditUserWindow();
  }

  @Override
  protected void createFields(FormPanel panel, FormData formData) {
    //TODO: implement this method
  }

  @Override
  protected void onSave() {
    //TODO: implement this method
  }
}
