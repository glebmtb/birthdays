package ru.n5g.birthdays.core.client.util;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * @author Донской А.
 */
public class IconUtils {

  public static AbstractImagePrototype getAbstractImagePrototype(ImageResource icon) {
    return IconHelper.createPath(icon.getURL(), icon.getWidth(), icon.getHeight());
  }

  public static void setBtnIcon(Button btn, ImageResource icon) {
    btn.setIcon(getAbstractImagePrototype(icon));
  }

  public static void setLargeButton(Button button, ImageResource resource, String text) {
    setBtnIcon(button, resource);
    button.setToolTip(text);
    button.setScale(Style.ButtonScale.LARGE);
    setTestId(button, text);
  }

  public static void setSmallButton(Button button, ImageResource resource, String text) {
    setBtnIcon(button, resource);
    button.setToolTip(text);
    button.setScale(Style.ButtonScale.SMALL);
    setTestId(button, text);
  }

  public static void setTestId(Button button, String text) {
    TestIdSetter.setTestIdIfAbsent(button, "btn_201110261212" + (text != null && !text.trim().isEmpty() ? "_" + text.trim() : ""));
  }
}
