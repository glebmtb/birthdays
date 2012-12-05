package ru.n5g.birthdays.core.server.service.combo_box;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.n5g.birthdays.core.shared.combo_box.ComboBoxFilter;

/**
 * @author belyaev
 */
@RemoteServiceRelativePath("loadComboBoxService")
public interface LoadComboBoxService<T extends ModelData> extends RemoteService {
  BasePagingLoadResult<T> load(BasePagingLoadConfig loadConfig);

  BasePagingLoadResult<T> load(BasePagingLoadConfig loadConfig, List<ComboBoxFilter> filters);
}
