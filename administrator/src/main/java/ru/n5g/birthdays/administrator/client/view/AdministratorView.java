package ru.n5g.birthdays.administrator.client.view;

import com.google.gwt.user.client.ui.IsWidget;
import ru.n5g.birthdays.administrator.shared.bean.AdministratorListDTO;

public interface AdministratorView<M extends AdministratorListDTO> extends IsWidget{
  void refresh();
}
