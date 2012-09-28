package ru.n5g.birthdays.note_book.client.view;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import ru.n5g.birthdays.components.client.view.SimpleWindowViewImpl;
import ru.n5g.birthdays.core.client.widget.form.TrimTextAreaField;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.core.shared.bean.ActionEnum;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.note_book.client.localization.ContactEditLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactEditPresenter;

/**
 * @author belyaev
 */
public class ContactEditWindowImplImpl extends SimpleWindowViewImpl implements ContactEditPresenter.ContactEditWindow {
  private ContactEditPresenter presenter;
  private ContactEditLocalization localization;

  private ActionEnum action;
  private ContactDTO dto;

  private TrimTextField nickname;
  private TrimTextField lastName;
  private TrimTextField firstName;
  private TrimTextField middleName;
  private TrimTextAreaField comment;


  public ContactEditWindowImplImpl(ContactEditPresenter presenter, ContactEditLocalization localization, ActionEnum action, ContactDTO dto) {
    super(presenter);
    this.setResizable(false);
    this.presenter = presenter;
    this.localization = localization;
    this.action = action;
    this.dto = dto;
    setWindowHeight(320);
  }

  @Override
  protected String getTitleWindow() {
    if (action == ActionEnum.ADD) {
      return localization.getAddTitle();
    }
    else if (action == ActionEnum.EDIT) {
      return localization.getEditTitle();
    }
    return "";
  }

  @Override
  protected void createFields(FormPanel panel, FormData formData) {
    nickname = createTextField(255, localization.nickname(), "text_20120925144901", false);
    lastName = createTextField(255, localization.lastName(), "text_20120925145002", false);
    firstName = createTextField(255, localization.firstName(), "text_20120925145003", false);
    middleName = createTextField(255, localization.middleName(), "text_2012092514504", false);
    comment = createTextAreaField(1000, localization.comment(), "text_20120925145005", false, 100);

    panel.add(nickname, formData);
    panel.add(lastName, formData);
    panel.add(firstName, formData);
    panel.add(middleName, formData);
    panel.add(comment, formData);

    if (action == ActionEnum.EDIT)
      onWrittenFields();
  }

  private void onWrittenFields() {
    nickname.setValue(dto.getNickname());
    lastName.setValue(dto.getLastName());
    firstName.setValue(dto.getFirstName());
    middleName.setValue(dto.getMiddleName());
    comment.setValue(dto.getComment());
  }

  @Override
  protected void onSave() {
    dto.setNickname(nickname.getValue());
    dto.setLastName(lastName.getValue());
    dto.setFirstName(firstName.getValue());
    dto.setMiddleName(middleName.getValue());
    dto.setComment(comment.getValue());
    presenter.save(dto);
  }

  @Override
  protected String getLabelBtnApply() {
    if (action == ActionEnum.EDIT)
      return localization.getLabelEdit();

    return super.getLabelBtnApply();
  }
}
