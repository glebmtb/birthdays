package ru.n5g.birthdays.administrator.event_type.client.view;

import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import ru.n5g.birthdays.administrator.event_type.client.localization.EventTypeEditLocalization;
import ru.n5g.birthdays.administrator.event_type.client.presenter.EventTypeEditPresenter;
import ru.n5g.birthdays.components.client.view.SimpleCreateField;
import ru.n5g.birthdays.components.client.view.SimpleWindowViewImpl;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.core.shared.bean.ActionEnum;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

/**
 * @author belyaev
 */
public class EventTypeEditWindow extends SimpleWindowViewImpl implements EventTypeEditPresenter.View {
  private EventTypeEditPresenter presenter;
  private EventTypeEditLocalization localization;

  private ActionEnum action;
  private EventTypeDTO dto;

  private TrimTextField eventTypeName;
  private CheckBox singleCheckBox;


  public EventTypeEditWindow(EventTypeEditPresenter presenter, EventTypeEditLocalization localization, ActionEnum action, EventTypeDTO dto) {
    super(presenter);
    this.setResizable(false);
    this.presenter = presenter;
    this.localization = localization;
    this.action = action;
    this.dto = dto;
    setWindowHeight(160);
    setLabelWidth(240);
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
    eventTypeName = SimpleCreateField.createTextField(255, localization.eventTypeName(), "text_20120925144901", true);
    singleCheckBox = new CheckBox();
    singleCheckBox.setFieldLabel(localization.singleCheckBox());

    panel.add(eventTypeName, formData);
    panel.add(singleCheckBox);
    if (action == ActionEnum.EDIT)
      onWrittenFields();
  }

  private void onWrittenFields() {
    eventTypeName.setValue(dto.getName());
    singleCheckBox.setValue(dto.getSingle());
  }

  @Override
  protected void onSave() {
    dto.setName(eventTypeName.getValue());
    dto.setSingle(singleCheckBox.getValue());
    presenter.save(dto);
  }

  @Override
  protected String getLabelBtnApply() {
    if (action == ActionEnum.EDIT)
      return localization.getLabelEdit();
    return super.getLabelBtnApply();
  }
}
