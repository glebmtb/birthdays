package ru.n5g.birthdays.components.client.view;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import ru.n5g.birthdays.components.client.localization.SimpleWindowLocalization;
import ru.n5g.birthdays.components.client.presenter.SimpleWindowPresenter;
import ru.n5g.birthdays.core.client.util.RequiredFieldsUtil;
import ru.n5g.birthdays.core.client.util.TestIdSetter;
import ru.n5g.birthdays.core.client.widget.form.TrimTextField;

/**
 * @author home
 */
public abstract class SimpleWindowViewImpl extends Window implements SimpleWindowView {
  protected static final String MIN_DATE_STR = "01.01.1000";
  protected static final String MAX_DATE_STR = "31.12.9999";
  protected static final String DATE_PARSING_PATTERN = "dd.MM.yyyy";
  protected static final DateTimeFormat dateParsingFormat = DateTimeFormat.getFormat(DATE_PARSING_PATTERN);
  protected static final String LABEL_STYLE = "margin-top:4px";

  private boolean additionBtnApply = true;
  private int labelWidth = 150;
  private int windowWidth = 500;
  private int windowHeight = 250;

  private SimpleWindowLocalization localization;
  private SimpleWindowPresenter presenter;

  protected FormPanel panel;

  public SimpleWindowViewImpl(SimpleWindowPresenter presenter) {
    super();
    this.presenter = presenter;
    this.localization = presenter.getLocalization();
    setModal(true);
    setMinimizable(false);
    setMaximizable(false);
    setLayout(new FitLayout());
  }

  /**
   * Заголовок окна
   *
   * @return
   */
  protected abstract String getTitleWindow();

  public int getWindowMinHeight() {
    return windowHeight;
  }

  @Override
  protected void onRender(Element parent, int pos) {
    super.onRender(parent, pos);

    setHeading(getTitleWindow());
    setSize(windowWidth, windowHeight);
    setMinWidth(windowWidth);
    setMinHeight(windowHeight);

    FormData formData = new FormData("95%");
    panel = new FormPanel();
    panel.setHeaderVisible(false);
    panel.setLabelWidth(labelWidth);

    createFields(panel, formData);

    Button btnApply = new Button(getLabelBtnApply());
    btnApply.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onSave();
      }
    });


    Button btnCancel = new Button(getLabelBtnCancel());
    btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        hide();
      }
    });

    panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
    FormButtonBinding btnBinding = new FormButtonBinding(panel);
    btnBinding.addButton(btnApply);

    if (additionBtnApply)
      panel.addButton(btnApply);
    panel.addButton(btnCancel);
    add(panel);
  }

  /**
   * Добавление кнопки сохранить на форму
   *
   * @param additionBtnApply -  true - кнопка будет добавлена;
   *                         false - кнопки сохранить не будет на панели
   */
  public void setAdditionBtnApply(boolean additionBtnApply) {
    this.additionBtnApply = additionBtnApply;
  }

  /**
   * Кнопка отмены
   * localization.btnCancel()
   *
   * @return
   */
  protected String getLabelBtnCancel() {
    return localization.btnCancel();
  }

  /**
   * Кнопка Сохранения
   * localization.btnApply()
   *
   * @return
   */
  protected String getLabelBtnApply() {
    return localization.btnApply();
  }

  /**
   * Example
   * <p/>
   * TrimTextField
   * number = new TrimTextField();
   * number.setMaxLength(100);
   * number.setFieldLabel(localization.number());
   * number.setLabelStyle(LABEL_STYLE);
   * TestIdSetter.resetTestId(number, "form_201208251452");
   * RequiredFieldsUtil.setRequired(number, true);
   * <p/>
   * <p/>
   * MaskedDateField
   * validTo = new MaskedDateField();
   * validTo.setMinValue(dateParsingFormat.parse(MIN_DATE_STR));
   * validTo.setMaxValue(dateParsingFormat.parse(MAX_DATE_STR));
   * validTo.getPropertyEditor().setFormat(DateTimeFormat.getFormat(DATE_PARSING_PATTERN));
   * TestIdSetter.resetTestId(validTo, "form_201208251448");
   * validTo.setFieldLabel(localization.validTo());
   * validTo.setLabelStyle(LABEL_STYLE);
   * validTo.setFireChangeEventOnSetValue(true);
   * RequiredFieldsUtil.setRequired(validTo, true);
   * <p/>
   * Add to panel
   * panel.add(number, formData);
   * panel.add(validTo, formData);
   *
   * @param panel    - FormPanel
   * @param formData - FormData("95%")
   */
  protected abstract void createFields(FormPanel panel, FormData formData);

  protected abstract void onSave();


  public int getLabelWidth() {
    return labelWidth;
  }

  /**
   * задать длину ширину у филдов
   *
   * @param labelWidth - ширина лейбла
   */
  public void setLabelWidth(int labelWidth) {
    this.labelWidth = labelWidth;
  }

  public int getWindowWidth() {
    return windowWidth;
  }


  /**
   * задать ширину окна
   *
   * @param windowWidth - ширина окна
   */
  public void setWindowWidth(int windowWidth) {
    this.windowWidth = windowWidth;
  }

  public int getWindowHeight() {
    return windowHeight;
  }

  /**
   * задать высоту окна
   *
   * @param windowHeight - высота окна
   */
  public void setWindowHeight(int windowHeight) {
    this.windowHeight = windowHeight;
  }

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

  public static TextArea createTextAreaField(int maxLength, String fieldLabel, String testId, boolean isRequired, int height) {
    TextArea trimTextField;
    trimTextField = new TextArea();
    trimTextField.setMaxLength(maxLength);
    trimTextField.setFieldLabel(fieldLabel);
    trimTextField.setLabelStyle(LABEL_STYLE);
    trimTextField.setHeight(height);
    TestIdSetter.resetTestId(trimTextField, testId);
    RequiredFieldsUtil.setRequired(trimTextField, isRequired);
    return trimTextField;
  }
}
