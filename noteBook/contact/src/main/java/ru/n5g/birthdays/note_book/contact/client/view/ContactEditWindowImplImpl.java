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
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import ru.n5g.birthdays.components.client.view.SimpleWindowViewImpl;
import ru.n5g.birthdays.core.client.combo_box.AdvancedComboBox;
import ru.n5g.birthdays.core.client.util.TestIdSetter;
import ru.n5g.birthdays.core.client.widget.form.TrimTextAreaField;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.core.shared.bean.ActionEnum;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilterType;
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

  private AdvancedComboBox<EventTypeDTO> eventTypeComboBox;
  private EditorGrid<BaseModelData> eventGrid;

  protected static final String DATE_PARSING_PATTERN = "dd.MM.yyyy";

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
    addButton();
  }

  private void addButton() {
    btnApply = new Button(localization.getLabelEdit());
    btnApply.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onSave();
      }
    });


    Button btnCancel;
    btnCancel = new Button(localization.btnClose());
    btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        hide();
      }
    });

    setButtonAlign(Style.HorizontalAlignment.CENTER);

    addButton(btnApply);
    addButton(btnCancel);
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);

    ContentPanel cp = new ContentPanel();
    cp.setHeaderVisible(false);
    cp.setFrame(true);
    cp.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

    FormData formData = new FormData("100%");
    FormPanel panel = new FormPanel();
    panel.setHeaderVisible(false);
    panel.setLabelWidth(100);
    createFields(panel, formData);

    FieldSet contactFieldSet = new FieldSet();
    contactFieldSet.setHeading(localization.contactFiledSet());
    contactFieldSet.setLayout(new FitLayout());
    contactFieldSet.add(panel);

    FieldSet eventFieldSet = new FieldSet();
    eventFieldSet.setHeading(localization.eventFieldSet());
    eventFieldSet.setLayout(new FitLayout());
    eventFieldSet.add(createEventPanel());

    RowData data = new RowData(.5, 1);
    data.setMargins(new Margins(5));

    cp.add(contactFieldSet, data);
    cp.add(eventFieldSet, data);
    add(cp);
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


  protected void createFields(FormPanel panel, FormData formData) {
    nickname = SimpleWindowViewImpl.createTextField(255, localization.nickname(), "text_20120925144901", false);
    lastName = SimpleWindowViewImpl.createTextField(255, localization.lastName(), "text_20120925145002", false);
    firstName = SimpleWindowViewImpl.createTextField(255, localization.firstName(), "text_20120925145003", false);
    middleName = SimpleWindowViewImpl.createTextField(255, localization.middleName(), "text_2012092514504", false);
    comment = SimpleWindowViewImpl.createTextAreaField(1000, localization.comment(), "text_20120925145005", false, 100);

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


  protected void onSave() {
    dto.setNickname(nickname.getValue());
    dto.setLastName(lastName.getValue());
    dto.setFirstName(firstName.getValue());
    dto.setMiddleName(middleName.getValue());
    dto.setComment(comment.getValue());
    presenter.save(dto);
  }

  private LayoutContainer createEventPanel() {

    eventTypeComboBox = new AdvancedComboBox<EventTypeDTO>();
    eventTypeComboBox.setRemoteFilterType(ComboBoxFilterType.ILIKE);
    eventTypeComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
    eventTypeComboBox.setForceSelection(true);
    eventTypeComboBox.setLoadingText(localization.comboBoxLoading());
    eventTypeComboBox.setInitializingText(localization.comboBoxInitialization());
    eventTypeComboBox.setStore(presenter.getEventTypeComboBoxStore());
    eventTypeComboBox.setDisplayField(EventTypeDTO.NAME);
    eventTypeComboBox.setWidth(282);
    TestIdSetter.resetTestId(eventTypeComboBox, "form_2012082514453");

    Button addEventTypeButton = new Button();
    addEventTypeButton.setTitle(localization.addEventType());
    addEventTypeButton.addStyleName("btn-add-16");


    HorizontalPanel addEventPanel = new HorizontalPanel();
    addEventPanel.add(eventTypeComboBox);
    addEventPanel.add(addEventTypeButton);

    LayoutContainer eventPanel = new LayoutContainer();
    eventPanel.setLayout(new FormLayout());
    eventPanel.add(addEventPanel);
    eventPanel.add(test());
    return eventPanel;
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
}
