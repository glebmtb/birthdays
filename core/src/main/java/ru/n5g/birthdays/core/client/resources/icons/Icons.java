package ru.n5g.birthdays.core.client.resources.icons;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author belyaev
 */
public interface Icons extends ClientBundle {
  @Source("x16/add.png")
  ImageResource add16();

  @Source("x32/add.png")
  ImageResource add32();

  @Source("x32/edit.png")
  ImageResource edit32();

  @Source("x16/edit.png")
  ImageResource edit16();

  @Source("x16/delete.png")
  ImageResource delete16();

  @Source("x32/delete.png")
  ImageResource delete32();

  @Source("x16/refresh.png")
  ImageResource refresh16();

  @Source("x32/refresh.png")
  ImageResource refresh32();

  @Source("note.png")
  ImageResource note();

  @Source("clock.png")
  ImageResource clock();

  @Source("card.png")
  ImageResource card();

  @Source("zoomIn.png")
  ImageResource zoomIn();

  @Source("zoomOut.png")
  ImageResource zoomOut();

  @Source("x32/download.png")
  ImageResource download();

  @Source("x32/button_ok.png")
  ImageResource buttonOk();

  @Source("x32/button_cancel.png")
  ImageResource buttonCancel();
}
