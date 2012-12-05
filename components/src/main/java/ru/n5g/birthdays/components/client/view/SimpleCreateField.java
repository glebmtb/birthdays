package ru.n5g.birthdays.components.client.view;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import ru.n5g.birthdays.core.client.combo_box.AdvancedComboBox;
import ru.n5g.birthdays.core.client.util.RequiredFieldsUtil;
import ru.n5g.birthdays.core.client.util.TestIdSetter;
import ru.n5g.birthdays.core.client.widget.form.TrimTextAreaField;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilterType;

/**
 * @author home
 */
public class SimpleCreateField {
  protected static final String MIN_DATE_STR = "01.01.1000";
  protected static final String MAX_DATE_STR = "31.12.9999";
  protected static final String DATE_PARSING_PATTERN = "dd.MM.yyyy";
  protected static final DateTimeFormat dateParsingFormat = DateTimeFormat.getFormat(DATE_PARSING_PATTERN);
  protected static final String LABEL_STYLE = "margin-top:4px";

  public static TrimTextField createTextField(int maxLength, String fieldLabel, String testId, boolean isRequired) {
    TrimTextField trimTextField;
    trimTextField = new TrimTextField();
    trimTextField.setMaxLength(maxLength);
    trimTextField.setFieldLabel(fieldLabel);
    trimTextField.setLabelStyle(LABEL_STYLE);
    TestIdSetter.resetTestId(trimTextField, testId);
    RequiredFieldsUtil.setRequired(trimTextField, isRequired);
    return trimTextField;
  }

  public static TrimTextAreaField createTextAreaField(int maxLength, String fieldLabel, String testId, boolean isRequired, int height) {
    TrimTextAreaField trimTextField;
    trimTextField = new TrimTextAreaField();
    trimTextField.setMaxLength(maxLength);
    trimTextField.setFieldLabel(fieldLabel);
    trimTextField.setLabelStyle(LABEL_STYLE);
    trimTextField.setHeight(height);
    TestIdSetter.resetTestId(trimTextField, testId);
    RequiredFieldsUtil.setRequired(trimTextField, isRequired);
    return trimTextField;
  }

  public static AdvancedComboBox createComboBox(String loadingText, String initializingText, ListStore store, String displayField, String testId, boolean isRequired) {
    AdvancedComboBox comboBox = new AdvancedComboBox();
    comboBox.setRemoteFilterType(ComboBoxFilterType.ILIKE);
    comboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
    comboBox.setForceSelection(true);
    comboBox.setLoadingText(loadingText);
    comboBox.setInitializingText(initializingText);
    comboBox.setStore(store);
    comboBox.setDisplayField(displayField);
    RequiredFieldsUtil.setRequired(comboBox, isRequired);
    TestIdSetter.resetTestId(comboBox, testId);
    return comboBox;
  }

  public static Button createButtonWithIcon(String styleName, String btnName, String testId, SelectionListener<ButtonEvent> listener) {
    Button button = new Button();
    button.addStyleName(styleName);
    button.setToolTip(btnName);
    button.setScale(Style.ButtonScale.LARGE);
    TestIdSetter.resetTestId(button, testId);
    button.addSelectionListener(listener);
    return button;
  }

  public static FieldSet createFieldSet(String heading) {
    FieldSet fieldSet;
    fieldSet = new FieldSet();
    fieldSet.setHeading(heading);
    fieldSet.setLayout(new FitLayout());
    return fieldSet;
  }

  public static Button createButton(String btnName, SelectionListener<ButtonEvent> listener, String testId) {
    Button button;
    button = new Button(btnName);
    button.addSelectionListener(listener);
    TestIdSetter.resetTestId(button, testId);
    return button;
  }

  public static ColumnConfig columnConfigSortable(String id, String name, int width, boolean sortable) {
    ColumnConfig columnConfig = new ColumnConfig(id, name, width);
    columnConfig.setSortable(sortable);
    columnConfig.setMenuDisabled(true);
    return columnConfig;
  }

  public static<М extends ModelData> StoreFilterField createStoreFilterField(final Grid<М> grid) {
    GridFilters filters = new GridFilters();
    final StringFilter stringFilter = new StringFilter("storeFilterField");
    filters.addFilter(stringFilter);
    grid.addPlugin(filters);
    StoreFilterField<М> filter = new StoreFilterField<М>() {
      @Override
      protected boolean doSelect(Store<М> store, М parent, М record, String property, String filter) {
        return false;
      }
      @Override
      protected void onFilter () {
        focus();
        stringFilter.setValue(getRawValue());
      }
    };
    return filter;
  }
}
