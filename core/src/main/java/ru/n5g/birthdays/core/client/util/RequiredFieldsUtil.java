package ru.n5g.birthdays.core.client.util;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.form.*;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author belyaev
 */
public class RequiredFieldsUtil {

  private static void setLabelStyle(Field field, boolean required) {
    field.setMessageTarget("tooltip");

    if (!field.isRendered()) {
      field.setLabelSeparator(required ? ":<span class=\"redstar\">*</span>" : ":");
      field.setLabelStyle("font-size:" + (required ? "18px" : ""));
    }
    else {
      El labelElement = getLabelElement(field);
      if (labelElement != null) {
        labelElement.setInnerHtml(field.getFieldLabel() + (required ? ":<span class=\"redstar\">*</span>" : ":"));
        labelElement.setStyleAttribute("font-size", required ? "18px" : "");
      }
    }
  }

  private static El getLabelElement(Field field) {
    Widget parent = field.getParent();
    assert parent instanceof Component : "Parent element must be a gxt component";
    boolean input = !(field instanceof MultiField);
    return ((Component) parent).el().selectNode("label[for=" + field.getId() + (input ? "-input]" : "]"));
  }

  public static void setRequired(TextField field, boolean required) {
    field.setAllowBlank(!required);
    setLabelStyle(field, required);
  }

  public static void setRequired(NumberField field, boolean required) {
    field.setAllowBlank(!required);
    setLabelStyle(field, required);
  }

  public static void setRequired(ComboBox field, boolean required) {
    field.setAllowBlank(!required);
    setLabelStyle(field, required);
  }

  public static void setRequired(CheckBox field, boolean required) {
    setLabelStyle(field, required);
  }

  public static void setRequired(RadioGroup field, boolean required) {
    setLabelStyle(field, required);
  }

  public static void setRequired(CheckBoxGroup field, boolean required) {
    setLabelStyle(field, required);
  }
}