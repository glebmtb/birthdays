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
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import ru.n5g.birthdays.components.client.view.SimpleCreateField;
import ru.n5g.birthdays.core.client.combo_box.AdvancedComboBox;
import ru.n5g.birthdays.core.client.widget.form.TrimTextAreaField;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.core.shared.bean.ActionEnum;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.note_book.contact.client.localization.ContactEditLocalization;
import ru.n5g.birthdays.note_book.contact.client.presenter.ContactEditPresenter;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactEditDTO;
import ru.n5g.birthdays.note_book.contact.shared.bean.EventListDTO;

/**
 * @author belyaev
 */
public class ContactEditWindowImplImpl extends Window implements ContactEditPresenter.ContactEditWindow {
  private ContactEditPresenter presenter;
  private ContactEditLocalization localization;
  private ActionEnum action;
  private ContactEditDTO dto;

  private Button btnApply;

  private TrimTextField nickname;
  private TrimTextField lastName;
  private TrimTextField firstName;
  private TrimTextField middleName;
  private TrimTextAreaField comment;

  private AdvancedComboBox eventTypeComboBox;
  private Button addEventTypeButton;
  private Grid<EventListDTO> eventGrid;
  private List<EventListDTO> eventListSave = new ArrayList<EventListDTO>(0);

  private static final FormData FORM_DATE = new FormData("100%");

  public ContactEditWindowImplImpl(ContactEditPresenter presenter, ContactEditLocalization localization, ActionEnum action, ContactEditDTO dto) {
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
    eventTypeComboBox.addListener(Events.Change, new Listener<BaseEvent>() {
      @Override
      public void handleEvent(BaseEvent be) {
        addEventTypeButton.setEnabled(eventTypeComboBox.getValue() != null);
      }
    });


    addEventTypeButton = SimpleCreateField.createButtonWithIcon("btn-add-16", localization.addEventType()
        , "button_201210071212", createSelectionListenerAddEventTypeButton());
    addEventTypeButton.disable();

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
        checkBox.setToolTip(localization.remind());
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

    column = new ColumnConfig(EventListDTO.EVENT_TYPE.concat(".").concat(EventTypeDTO.NAME), 100);
    column.setMenuDisabled(true);
    column.setFixed(true);
    columns.add(column);

    column = new ColumnConfig(EventListDTO.DATE_EVENT, 60);
    GridCellRenderer<EventListDTO> eventDate = new GridCellRenderer<EventListDTO>() {
      @Override
      public Object render(final EventListDTO model, final String property, ColumnData config, int rowIndex, int colIndex, ListStore<EventListDTO> store, Grid<EventListDTO> grid) {
        DateField dateField = new DateField();
        dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd.MM"));
        dateField.setWidth(60);
        dateField.setToolTip(localization.eventDate());
        dateField.setValue((Date) model.get(property));
        dateField.addListener(Events.Change, new Listener<BaseEvent>() {
          @Override
          public void handleEvent(BaseEvent be) {
            model.set(property, ((FieldEvent) be).getField().getValue());
            if (((FieldEvent) be).getField().isValid() && !eventListSave.contains(model)) {
              eventListSave.add(model);
            }
          }
        });
        return dateField;
      }
    };
    column.setRenderer(eventDate);
    column.setFixed(true);
    column.setMenuDisabled(true);
    columns.add(column);

    column = new ColumnConfig(EventListDTO.YEAR, 35);
    GridCellRenderer<EventListDTO> eventYearDate = new GridCellRenderer<EventListDTO>() {
      @Override
      public Object render(final EventListDTO model, final String property, ColumnData config, int rowIndex, int colIndex, ListStore<EventListDTO> store, Grid<EventListDTO> grid) {
        NumberField year = new NumberField();
        year.getImages().setInvalid(null);
        year.setToolTip(localization.eventYear());
        year.setMinValue(1900);
        year.setMaxValue(2100);
        year.setMinLength(4);
        year.setMaxLength(4);
        year.setFormat(NumberFormat.getFormat("####"));
        year.setWidth(35);
        year.setValue((Number) model.get(property));
        year.addListener(Events.Change, new Listener<BaseEvent>() {
          @Override
          public void handleEvent(BaseEvent be) {
            model.setYear((Number) ((FieldEvent) be).getField().getValue());
            if (((FieldEvent) be).getField().isValid() && !eventListSave.contains(model)) {
              eventListSave.add(model);
            }
          }
        });
        return year;
      }
    };
    column.setRenderer(eventYearDate);
    column.setFixed(true);
    column.setMenuDisabled(true);
    columns.add(column);

    GridCellRenderer<BaseModelData> editEventButton = new GridCellRenderer<BaseModelData>() {
      @Override
      public Object render(BaseModelData model, String property, ColumnData config, final int rowIndex, int colIndex, ListStore<BaseModelData> store, final Grid<BaseModelData> grid) {
        Button button = new Button();
        button.addStyleName("btn-kontact-date-16");
        button.setToolTip(localization.editEvent());
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

    GridCellRenderer<EventListDTO> deleteEventButton = new GridCellRenderer<EventListDTO>() {
      @Override
      public Object render(final EventListDTO model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<EventListDTO> store, Grid<EventListDTO> grid) {
        Button button = new Button();
        button.setBorders(false);
        button.addStyleName("btn-cancel-16");
        button.setToolTip(localization.deleteEvent());
        button.addSelectionListener(new SelectionListener<ButtonEvent>() {
          @Override
          public void componentSelected(ButtonEvent ce) {
            eventGrid.getStore().remove(model);
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
    ListStore<EventListDTO> store = new ListStore<EventListDTO>() {
      @Override
      public void remove(int index) {
        EventListDTO dto = getAt(index);
        if (dto.getId() != null) {
          dto.setDelete(true);
          eventListSave.add(dto);
        }
        else if (eventListSave.contains(dto)) {
          eventListSave.remove(dto);
        }
        super.remove(index);
      }
    };
    if (dto.getEventList() != null)
      store.add(dto.getEventList());
    eventGrid = new Grid<EventListDTO>(store, cm);
    eventGrid.setHeight(245);
    eventGrid.setBorders(true);
    eventGrid.setHideHeaders(true);
    eventGrid.setLoadMask(true);
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
    dto.setEventList(eventListSave);
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
        btnApply.disable();
      }
    };
  }

  private SelectionListener<ButtonEvent> createSelectionListenerAddEventTypeButton() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        EventListDTO dto = new EventListDTO();
        dto.setEventType((EventTypeDTO) eventTypeComboBox.getValue());
        eventGrid.getStore().insert(dto, 0);
        eventTypeComboBox.clear();
        addEventTypeButton.disable();
      }
    };
  }
}
