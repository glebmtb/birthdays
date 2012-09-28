package ru.n5g.birthdays.core.client.widget.form;

import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * @author belyaev
 */
public class TrimTextAreaField extends TextArea {

  @Override
  public String getValue() {
    String value = super.getValue();
    String trimmedValue;
    if (value != null && !(trimmedValue = value.trim()).isEmpty()) {
      return SafeHtmlUtils.fromString(trimmedValue).asString();
    }
    else {
      return null;
    }
  }

  @Override
  public String getRawValue() {
    String value = super.getRawValue();
    if (value != null) {
      return value.trim();
    }
    else {
      return null;
    }
  }
}
