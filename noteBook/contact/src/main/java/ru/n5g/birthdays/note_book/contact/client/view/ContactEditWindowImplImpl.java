package ru.n5g.birthdays.note_book.contact.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
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
  private Button btnApplyAndClose;
  private Button btnCancel;

  private TrimTextField nickname;
  private TrimTextField lastName;
  private TrimTextField firstName;
  private TrimTextField middleName;
  private TrimTextAreaField comment;

  private AdvancedComboBox<EventTypeDTO> eventTypeComboBox;

  protected static final String MIN_DATE_STR = "01.01.1000";
  protected static final String MAX_DATE_STR = "31.12.9999";
  protected static final String DATE_PARSING_PATTERN = "dd.MM.yyyy";
  protected static final DateTimeFormat dateParsingFormat = DateTimeFormat.getFormat(DATE_PARSING_PATTERN);
  protected static final String LABEL_STYLE = "margin-top:4px";

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

    btnApplyAndClose = new Button(localization.getLabelEditAndClose());
    btnApplyAndClose.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onSave();
        hide();
      }
    });

    btnCancel = new Button(localization.btnCancel());
    btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        hide();
      }
    });

    setButtonAlign(Style.HorizontalAlignment.CENTER);

    addButton(btnApply);
    addButton(btnApplyAndClose);
    addButton(btnCancel);
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);

    ContentPanel cp = new ContentPanel();
    cp.setHeading(getTitleWindow());
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
    ColumnConfig column = new ColumnConfig("name", 200);
    column.setRowHeader(false);
    column.setMenuDisabled(true);
    columns.add(column);

    GridCellRenderer<BaseModelData> editButton = new GridCellRenderer<BaseModelData>() {
      @Override
      public Object render(BaseModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<BaseModelData> store, Grid<BaseModelData> grid) {
        return new Button((String) model.get(property));
      }
    };

    column = new ColumnConfig("day", 50);
    column.setRenderer(editButton);
    columns.add(column);
    ColumnModel cm = new ColumnModel(columns);
    ListStore<BaseModelData> store = new ListStore<BaseModelData>();
    BaseModelData baseModelData = new BaseModelData();
    baseModelData.set("name", "День рождение");
    baseModelData.set("day", "22.05.1988");
    store.add(baseModelData);
    Grid<BaseModelData> grid = new Grid<BaseModelData>(store, cm);


    return grid;
  }


}
