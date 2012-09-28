package ru.n5g.birthdays.core.client.widget.form;

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;

/**
 * @author belyaev
 */
public class TrimTextField extends TextField<String> {

  @Override
  public String getValue() {
    String value = super.getValue();
    String trimmedValue;
    if (value != null && !(trimmedValue = value.trim()).isEmpty()) {
      return SimpleHtmlSanitizer.sanitizeHtml(trimmedValue).asString();
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
