package ru.n5g.birthdays.note_book.contact.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import ru.n5g.birthdays.components.client.view.SimpleCreateField;
import ru.n5g.birthdays.core.client.combo_box.AdvancedComboBox;
import ru.n5g.birthdays.core.client.widget.form.TrimTextAreaField;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.core.shared.bean.ActionEnum;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.note_book.contact.client.localization.ContactEditLocalization;
import ru.n5g.birthdays.note_book.contact.client.presenter.ContactEditPresenter;

/**
 * @author belyaev
 */
public class ContactEditWindowImplImpl extends Window implements ContactEditPresenter.ContactEditWindow {
  private ContactEditPresenter presenter;
  private ContactEditLocalization localization;
  private ActionEnum action;
  private ContactDTO dto;

  private Button btnApply;

  private TrimTextField nickname;
  private TrimTextField lastName;
  private TrimTextField firstName;
  private TrimTextField middleName;
  private TrimTextAreaField comment;

  private AdvancedComboBox eventTypeComboBox;
  private EditorGrid<BaseModelData> eventGrid;

  private static final FormData FORM_DATE = new FormData("100%");

  public ContactEditWindowImplImpl(ContactEditPresenter presenter, ContactEditLocalization localization, ActionEnum action, ContactDTO dto) {
    super();
    this.setResizable(false);
    this.presenter = presenter;
    this.localization = localization;
    this.action = action;
    this.dto = dto;
    this.presenter = presenter;
    setModal(true);
    setMinimizable(false);
    setMaximizable(false);
    setLayout(new FitLayout());
    setSize(700, 400);
    setHeading(getTitleWindow());

    setButtonAlign(Style.HorizontalAlignment.CENTER);
    btnApply = SimpleCreateField.createButton(localization.getLabelEdit(), createSelectionListenerBtnApply(), "btn_201210071224");
    addButton(btnApply);
    addButton(SimpleCreateField.createButton(localization.btnClose(), createSelectionListenerBtnCancel(), "btn_201210071223"));
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);

    nickname = SimpleCreateField.createTextField(255, localization.nickname(), "text_20120925144901", false);
    lastName = SimpleCreateField.createTextField(255, localization.lastName(), "text_20120925145002", false);
    firstName = SimpleCreateField.createTextField(255, localization.firstName(), "text_20120925145003", false);
    middleName = SimpleCreateField.createTextField(255, localization.middleName(), "text_2012092514504", false);
    comment = SimpleCreateField.createTextAreaField(1000, localization.comment(), "text_20120925145005", false, 100);

    FormPanel panel = new FormPanel();
    panel.setHeaderVisible(false);
    panel.setLabelWidth(100);

    panel.add(nickname, FORM_DATE);
    panel.add(lastName, FORM_DATE);
    panel.add(firstName, FORM_DATE);
    panel.add(middleName, FORM_DATE);
    panel.add(comment, FORM_DATE);

    FieldSet contactFieldSet;
    contactFieldSet = SimpleCreateField.createFieldSet(localization.contactFiledSet());
    contactFieldSet.add(panel);

    eventTypeComboBox = SimpleCreateField.createComboBox(localization.comboBoxLoading()
        , localization.comboBoxInitialization(), presenter.getEventTypeComboBoxStore()
        , EventTypeDTO.NAME, "combobox_2012082514453", false);
    eventTypeComboBox.setWidth(282);

    Button addEventTypeButton;
    addEventTypeButton = SimpleCreateField.createButtonWithIcon("btn-add-16", localization.addEventType()
        , "button_201210071212", null);

    HorizontalPanel addEventPanel;
    addEventPanel = new HorizontalPanel();
    addEventPanel.setStyleAttribute("padding-bottom", "3px");
    addEventPanel.add(eventTypeComboBox);
    addEventPanel.add(addEventTypeButton);

    LayoutContainer eventPanel;
    eventPanel = new LayoutContainer();
    eventPanel.setLayout(new FormLayout());
    eventPanel.add(addEventPanel);
    eventPanel.add(test());

    FieldSet eventFieldSet;
    eventFieldSet = SimpleCreateField.createFieldSet(localization.eventFieldSet());
    eventFieldSet.add(eventPanel);

    RowData data = new RowData(.5, 1);
    data.setMargins(new Margins(5));

    ContentPanel cp = new ContentPanel();
    cp.setHeaderVisible(false);
    cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
    cp.setFrame(true);

    cp.add(contactFieldSet, data);
    cp.add(eventFieldSet, data);
    add(cp);

    if (action == ActionEnum.EDIT)
      onWrittenFields();
  }

  private Widget test() {
    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

    GridCellRenderer<BaseModelData> remindCheckBox = new GridCellRenderer<BaseModelData>() {
      @Override
      public Object render(final BaseModelData model, final String property, ColumnData config, final int rowIndex, int colIndex, ListStore<BaseModelData> store, final Grid<BaseModelData> grid) {
        final CheckBox checkBox = new CheckBox();
        checkBox.setToolTip("Напомить");
        checkBox.setValue((Boolean) model.get(property));
        checkBox.addListener(Events.Change, new Listener<BaseEvent>() {
          @Override
          public void handleEvent(BaseEvent be) {
            model.set(property, ((FieldEvent) be).getField().getValue());
          }
        });
        checkBox.disable();
        return checkBox;
      }
    };
    ColumnConfig column = new ColumnConfig("remindCheckBox", 25);
    column.setRenderer(remindCheckBox);
    column.setFixed(true);
    column.setMenuDisabled(true);
    columns.add(column);

    column = new ColumnConfig("name", 117);
    column.setMenuDisabled(true);
    column.setFixed(true);
    columns.add(column);

    DateField dateField = new DateField();
    dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat("MM.dd.yyyy"));
    column = new ColumnConfig("day", 80);
    column.setDateTimeFormat(DateTimeFormat.getFormat("dd MMM yyyy"));
    column.setMenuDisabled(true);
    column.setFixed(true);
    column.setEditor(new CellEditor(dateField));
    columns.add(column);

    GridCellRenderer<BaseModelData> editEventButton = new GridCellRenderer<BaseModelData>() {
      @Override
      public Object render(BaseModelData model, String property, ColumnData config, final int rowIndex, int colIndex, ListStore<BaseModelData> store, final Grid<BaseModelData> grid) {
        Button button = new Button();
        button.addStyleName("btn-kontact-date-16");
        button.setToolTip("Редактировать напоминание");
        button.addSelectionListener(new SelectionListener<ButtonEvent>() {
          @Override
          public void componentSelected(ButtonEvent ce) {

          }
        });
        button.disable();
        return button;
      }
    };
    column = new ColumnConfig("editEventButton", 35);
    column.setRenderer(editEventButton);
    column.setFixed(true);
    column.setMenuDisabled(true);
    columns.add(column);

    GridCellRenderer<BaseModelData> deleteEventButton = new GridCellRenderer<BaseModelData>() {
      @Override
      public Object render(BaseModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<BaseModelData> store, Grid<BaseModelData> grid) {
        Button button = new Button();
        button.setBorders(false);
        button.addStyleName("btn-cancel-16");
        button.setToolTip("Удалить событие");
        button.addSelectionListener(new SelectionListener<ButtonEvent>() {
          @Override
          public void componentSelected(ButtonEvent ce) {
            String st = "df";
          }
        });
        return button;
      }
    };
    column = new ColumnConfig("deleteEventButton", 35);
    column.setRenderer(deleteEventButton);
    column.setFixed(true);
    column.setMenuDisabled(true);
    columns.add(column);

    ColumnModel cm = new ColumnModel(columns);
    ListStore<BaseModelData> store = new ListStore<BaseModelData>();

    BaseModelData baseModelData = new BaseModelData();
    baseModelData.set("name", "День рождение");
    baseModelData.set("remindCheckBox", true);
    baseModelData.set("day", new Date(88, 05, 22));
    store.add(baseModelData);
    baseModelData = new BaseModelData();
    baseModelData.set("name", "День ангела");
    baseModelData.set("remindCheckBox", false);
    baseModelData.set("day", new Date(83, 01, 12));
    store.add(baseModelData);
    eventGrid = new EditorGrid<BaseModelData>(store, cm);
    eventGrid.setHeight(245);
    eventGrid.setBorders(true);
    eventGrid.setHideHeaders(true);
//    yourButton.setDisabled(yourGrid.store.getModifiedRecords().length === 0);
    return eventGrid;
  }

  private String getTitleWindow() {
    if (action == ActionEnum.ADD) {
      return localization.getAddTitle();
    }
    else if (action == ActionEnum.EDIT) {
      return localization.getEditTitle();
    }
    return "";
  }


  private void onWrittenFields() {
    nickname.setValue(dto.getNickname());
    lastName.setValue(dto.getLastName());
    firstName.setValue(dto.getFirstName());
    middleName.setValue(dto.getMiddleName());
    comment.setValue(dto.getComment());
  }


  protected void onSave() {
    dto.setNickname(nickname.getValue());
    dto.setLastName(lastName.getValue());
    dto.setFirstName(firstName.getValue());
    dto.setMiddleName(middleName.getValue());
    dto.setComment(comment.getValue());
    presenter.save(dto);
  }

  private SelectionListener<ButtonEvent> createSelectionListenerBtnCancel() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        hide();
      }
    };
  }

  private SelectionListener<ButtonEvent> createSelectionListenerBtnApply() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onSave();
      }
    };
  }
}
