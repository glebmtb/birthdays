package ru.n5g.birthdays.administrator.client.view;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import ru.n5g.birthdays.administrator.client.localization.AdministratorLocalization;
import ru.n5g.birthdays.administrator.client.presenter.AdministratorPresenter;
import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.components.client.view.SimpleWindowView;
import ru.n5g.birthdays.core.client.dialog.MyMessageBox;
import ru.n5g.birthdays.core.client.util.RequiredFieldsUtil;
import ru.n5g.birthdays.core.client.util.TestIdSetter;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.core.shared.bean.UsersDTO;

/**
 * @author belyaev
 */
public class EditUserWindow extends SimpleWindowView {
  private AdministratorLocalization localization;
  private AdministratorPresenter presenter;
  private UsersDTO dto;

  private TrimTextField login;
  //TODO добавить комбобокс с выбором роли
  private TrimTextField passwordRepetition;
  private TrimTextField password;


  public EditUserWindow(SimpleWindowPresenter presenter, UsersDTO dto) {
    super(presenter);
    this.dto = dto;
    this.presenter = (AdministratorPresenter) presenter;
    this.localization = (AdministratorLocalization) presenter.getLocalization();
  }

  @Override
  protected String getTitleWindow() {
    if (dto == null) {
      return localization.titleEditUserWindowAddNewUser();
    }
    return localization.titleEditUserWindow();
  }

  @Override
  protected void createFields(FormPanel panel, FormData formData) {
    login = new TrimTextField();
    login.setMaxLength(255);
    login.setFieldLabel(localization.login());
    login.setLabelStyle(LABEL_STYLE);
    login.setRegex("([a-zA-Z0-9]+)");
    login.getMessages().setRegexText(localization.errorOnlyLatinDigital());
    login.setValidator(new Validator() {
      @Override
      public String validate(Field<?> field, String value) {
//         if (value.charAt(0)) TODO добавить проверку на то что первый сивол не цифра
//          return localization.errorCodePoint();
        return null;
      }
    });
    TestIdSetter.resetTestId(login, "form_201208251452");
    RequiredFieldsUtil.setRequired(login, true);

    password = new TrimTextField();
    password.setMaxLength(255);
    password.setFieldLabel(localization.password());
    password.setLabelStyle(LABEL_STYLE);
    password.setPassword(true);
    password.setRegex("([a-zA-Z0-9]+)");
    password.getMessages().setRegexText(localization.errorOnlyLatinDigital());
    TestIdSetter.resetTestId(password, "form_201208251453");

    passwordRepetition = new TrimTextField();
    passwordRepetition.setMaxLength(255);
    passwordRepetition.setFieldLabel(localization.passwordRepetition());
    passwordRepetition.setLabelStyle(LABEL_STYLE);
    passwordRepetition.setPassword(true);
    TestIdSetter.resetTestId(passwordRepetition, "form_201208251454");

    if (dto == null) {
      RequiredFieldsUtil.setRequired(password, true);
      RequiredFieldsUtil.setRequired(passwordRepetition, true);
    }

    panel.add(login, formData);
    panel.add(password, formData);
    panel.add(passwordRepetition, formData);

    if (dto != null) {
      login.setValue(dto.getLogin());
    }
  }

  @Override
  protected void onSave() {
    if (checkPassword()) return;
    if (dto == null)
      dto = new UsersDTO();
    dto.setLogin(login.getValue());
    dto.setPassword(password.getValue());
    presenter.saveEditUserWindow(dto, this);
  }

  private boolean checkPassword() {
    if (password.getValue() != null
        && passwordRepetition.getValue() != null
        && !password.getValue().equals(passwordRepetition.getValue())) {
      MyMessageBox.error(localization.errorTitle(), localization.errorPassword(), null);
      return true;
    }
    return false;
  }

  @Override
  protected String getLabelBtnApply() {
    if (dto == null)
      return localization.btnAddUser();

    return super.getLabelBtnApply();
  }
}
