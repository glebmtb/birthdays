package ru.n5g.birthdays.note_book.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.LiveGridView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LiveToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import ru.n5g.birthdays.core.shared.bean.ContactDTO;
import ru.n5g.birthdays.note_book.client.localization.ContactListLocalization;
import ru.n5g.birthdays.note_book.client.presenter.ContactListPresenter;

public class ContactListViewImpl extends LayoutContainer implements ContactListPresenter.ContactView {

  private ContactListPresenter presenter;
  private ContactListLocalization localization;

  private Grid<ContactDTO> gridMain;
  private ToolBar toolBarTop;
  private ToolBar toolBarBottom;

  public ContactListViewImpl(ContactListPresenter presenter, ContactListLocalization localization) {
    super(new FitLayout());
    setBorders(false);

    this.presenter = presenter;
    this.localization = localization;
  }

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);

    ContentPanel cp;
    cp = new ContentPanel();
    cp.setHeaderVisible(false);
    cp.setLayout(new FitLayout());
    cp.setBorders(false);

    toolBarTop= new ToolBar();
    toolBarTop.setHeight(36);

    gridMain = createGrid();
    toolBarBottom = createToolBarBottom(gridMain);
    new QuickTip(gridMain);

    cp.setTopComponent(toolBarTop);
    cp.add(gridMain);
    cp.setBottomComponent(toolBarBottom);
    add(cp);
  }

  private Grid<ContactDTO> createGrid() {
    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

    columns.add(new ColumnConfig(ContactDTO.NICKNAME, localization.nickname(), 50));
    columns.add(new ColumnConfig((ContactDTO.LAST_NAME), localization.lastName(), 50));
    columns.add(new ColumnConfig(ContactDTO.FIRST_NAME, localization.firstName(), 50));
    columns.add(new ColumnConfig((ContactDTO.MIDDLE_NAME), localization.middleName(), 50));
    columns.add(new ColumnConfig((ContactDTO.COMMENT), localization.comment(), 100));


    ColumnModel cm = new ColumnModel(columns);
    ListStore store = presenter.loadContactList();

    LiveGridView liveView;
    liveView = new LiveGridView();
    liveView.setEmptyText(localization.listEmpty());

    Grid<ContactDTO> grid;
    grid = new Grid<ContactDTO>(store, cm);
    grid.setBorders(false);
    grid.setLoadMask(true);
    grid.setStripeRows(true);
    grid.setView(liveView);
    grid.getView().setAutoFill(true);
    grid.getView().setForceFit(true);
    grid.getView().setShowDirtyCells(false);
    grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
    return grid;
  }

  @Override
  public void refresh() {
    gridMain.getStore().getLoader().load();
  }

  private ToolBar createToolBarBottom(Grid<ContactDTO> grid) {
    ToolBar toolBarBottom;
    toolBarBottom = new ToolBar();
    LiveToolItem item;
    item = new LiveToolItem();
    item.bindGrid(grid);
    toolBarBottom.add(new FillToolItem());
    toolBarBottom.add(item);
    return toolBarBottom;
  }
}
