package ru.n5g.birthdays.note_book.client.view;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import ru.n5g.birthdays.components.client.view.SimpleWindowViewImpl;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.note_book.client.localization.ContactEditLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactEditPresenter;

/**
 * @author belyaev
 */
public class ContactEditWindowImplImpl extends SimpleWindowViewImpl implements ContactEditPresenter.ContactEditWindow {
  private ContactEditPresenter presenter;
  private ContactEditLocalization localization;

  private TrimTextField nickname;
  private TrimTextField lastName;
  private TrimTextField firstName;
  private TrimTextField middleName;
  private TrimTextField comment;


  public ContactEditWindowImplImpl(ContactEditPresenter presenter, ContactEditLocalization localization) {
    super(presenter);
    this.presenter = presenter;
    this.localization = localization;
  }

  @Override
  protected String getTitleWindow() {
    return localization.getAddTitle();
  }

  @Override
  protected void createFields(FormPanel panel, FormData formData) {
    nickname = createTextField(255, localization.nickname(), "text_20120925144901", false);
    lastName = createTextField(255, localization.lastName(), "text_20120925145002", false);
    firstName = createTextField(255, localization.firstName(), "text_20120925145003", false);
    middleName = createTextField(255, localization.middleName(), "text_2012092514504", false);
    comment = createTextField(255, localization.comment(), "text_20120925145005", false);

    panel.add(nickname, formData);
    panel.add(lastName, formData);
    panel.add(firstName, formData);
    panel.add(middleName, formData);
    panel.add(comment, formData);
  }

  @Override
  protected void onSave() {
    //TODO: implement this method
  }
}
