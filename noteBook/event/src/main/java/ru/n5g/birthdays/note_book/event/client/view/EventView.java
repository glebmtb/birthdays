package ru.n5g.birthdays.note_book.event.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
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
import ru.n5g.birthdays.core.client.util.TestIdSetter;
import ru.n5g.birthdays.core.shared.bean.EventDTO;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;
import ru.n5g.birthdays.note_book.event.client.localization.EventListLocalization;
import ru.n5g.birthdays.note_book.event.client.presenter.EventListPresenter;

/**
 * @author belyaev
 */
public class EventView extends LayoutContainer implements EventListPresenter.View {
  private EventListPresenter presenter;
  private EventListLocalization localization;

  private Grid<EventDTO> gridMain;
  private ToolBar toolBarTop;
  private ToolBar toolBarBottom;

  private Button btnRefresh;
  private Button btnAdd;
  private Button btnEdit;
  private Button btnDel;

  public EventView(EventListPresenter presenter, EventListLocalization localization) {
    super(new FitLayout());
    setBorders(false);
    this.presenter = presenter;
    this.localization = localization;
  }

  @Override
  protected void onRender(Element parent, int index) {
    super.onRender(parent, index);

    super.onRender(parent, index);

    ContentPanel cp;
    cp = new ContentPanel();
    cp.setHeaderVisible(false);
    cp.setLayout(new FitLayout());
    cp.setBorders(false);

//    btnAdd = createButtonWithIcon("btn-contact-new", localization.btnAdd(), "btn_20120925140802", createAddSelectionListener());
//    btnEdit = createButtonWithIcon("btn-contact-edit", localization.btnEdit(), "btn_20120925140803", createEditSelectionListener());
//    btnDel = createButtonWithIcon("btn-contact-delete", localization.btnDelete(), "btn_20120925140804", createDeleteSelectionListener());
    btnRefresh = createButton("btn-refresh-list", localization.btnRefresh(), "btn_20120925140401", createRefreshSelectionListener());
//    btnEdit.setEnabled(false);
//    btnDel.setEnabled(false);

    toolBarTop = new ToolBar();
//    toolBarTop.add(btnAdd);
//    toolBarTop.add(btnEdit);
//    toolBarTop.add(btnDel);
    toolBarTop.add(btnRefresh);

    gridMain = createGrid();
    toolBarBottom = createToolBarBottom(gridMain);
    new QuickTip(gridMain);

    cp.setTopComponent(toolBarTop);
    cp.add(gridMain);
    cp.setBottomComponent(toolBarBottom);
    add(cp);
  }

  private ToolBar createToolBarBottom(Grid<EventDTO> grid) {
    ToolBar toolBarBottom;
    toolBarBottom = new ToolBar();
    LiveToolItem item;
    item = new LiveToolItem();
    item.bindGrid(grid);
    toolBarBottom.add(new FillToolItem());
    toolBarBottom.add(item);
    return toolBarBottom;
  }

  private SelectionListener<ButtonEvent> createRefreshSelectionListener() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onRefresh();
      }
    };
  }

  private Button createButton(String styleName, String btnName, String testId, SelectionListener<ButtonEvent> listener) {
    Button button = new Button();
    button.addStyleName(styleName);
    button.setToolTip(btnName);
    button.setScale(Style.ButtonScale.LARGE);
    TestIdSetter.resetTestId(button, testId);
    button.addSelectionListener(listener);
    return button;
  }

  @Override
  public void onRefresh() {
    gridMain.getStore().getLoader().load();
  }

  private Grid<EventDTO> createGrid() {
    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

    columns.add(new ColumnConfig(EventDTO.EVENT_TYPE + "." + EventTypeDTO.NAME, localization.nameEventType(), 30));
    //имя контакта
    //дата события
    //дней осталось

    ColumnModel cm = new ColumnModel(columns);
    ListStore store = presenter.loadList();

    LiveGridView liveView;
    liveView = new LiveGridView();
    liveView.setEmptyText(localization.listEmpty());

    Grid<EventDTO> grid;
    grid = new Grid<EventDTO>(store, cm);
    grid.setBorders(false);
    grid.setLoadMask(true);
    grid.setStripeRows(true);
    grid.setView(liveView);
    grid.getView().setAutoFill(true);
    grid.getView().setForceFit(true);
    grid.getView().setShowDirtyCells(false);
    grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<EventDTO>() {
      @Override
      public void selectionChanged(SelectionChangedEvent<EventDTO> se) {
        int selSize = se.getSelection().size();
        boolean itemSelected = selSize > 0;
        boolean singleItemSelected = selSize == 1;
        enableButtons(itemSelected, singleItemSelected, se.getSelection());
      }
    });
    grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<EventDTO>>() {
      @Override
      public void handleEvent(GridEvent<EventDTO> be) {
        onGridDoubleClick(be);
      }
    });
    return grid;
  }

  private void enableButtons(boolean itemSelected, boolean singleItemSelected, List<EventDTO> selection) {
    btnEdit.setEnabled(singleItemSelected);
    btnDel.setEnabled(itemSelected);
  }

  private void onGridDoubleClick(GridEvent<EventDTO> be) {
//TODO
  }

}
