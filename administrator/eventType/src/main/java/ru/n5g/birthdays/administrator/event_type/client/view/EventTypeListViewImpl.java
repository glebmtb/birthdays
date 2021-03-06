package ru.n5g.birthdays.administrator.event_type.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LiveToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import ru.n5g.birthdays.administrator.event_type.client.localization.EventTypeListLocalization;
import ru.n5g.birthdays.administrator.event_type.client.presenter.EventTypeListPresenter;
import ru.n5g.birthdays.core.client.dialog.MyMessageBox;
import ru.n5g.birthdays.core.client.resources.Resources;
import ru.n5g.birthdays.core.client.util.TestIdSetter;
import ru.n5g.birthdays.core.shared.bean.EventTypeDTO;

public class EventTypeListViewImpl extends LayoutContainer implements EventTypeListPresenter.View {

  private EventTypeListPresenter presenter;
  private EventTypeListLocalization localization;

  private Grid<EventTypeDTO> gridMain;
  private ToolBar toolBarTop;
  private ToolBar toolBarBottom;

  private Button btnRefresh;
  private Button btnAdd;
  private Button btnEdit;
  private Button btnDel;

  public EventTypeListViewImpl(EventTypeListPresenter presenter, EventTypeListLocalization localization) {
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

    btnAdd = createButton("btn-add", localization.btnAdd(), "btn_20120925140802", createAddSelectionListener());
    btnEdit = createButton("btn-contact-edit", localization.btnEdit(), "btn_20120925140803", createEditSelectionListener());
    btnDel = createButton("btn-delete", localization.btnDelete(), "btn_20120925140804", createDeleteSelectionListener());
    btnRefresh = createButton("btn-refresh-list", localization.btnRefresh(), "btn_20120925140401", createRefreshSelectionListener());
    btnEdit.setEnabled(false);
    btnDel.setEnabled(false);

    toolBarTop = new ToolBar();
    toolBarTop.add(btnAdd);
    toolBarTop.add(btnEdit);
    toolBarTop.add(btnDel);
    toolBarTop.add(btnRefresh);

    gridMain = createGrid();
    toolBarBottom = createToolBarBottom(gridMain);
    new QuickTip(gridMain);

    cp.setTopComponent(toolBarTop);
    cp.add(gridMain);
    cp.setBottomComponent(toolBarBottom);
    add(cp);
  }

  private SelectionListener<ButtonEvent> createDeleteSelectionListener() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onDeleteContact();
      }
    };
  }

  private void onDeleteContact() {
    btnDel.disable();
    MyMessageBox.confirm(localization.dialogConfirm(), localization.confirmationDeleting(),
        new Listener<MessageBoxEvent>() {
          @Override
          public void handleEvent(MessageBoxEvent ce) {
            Button btn = ce.getButtonClicked();
            if (btn.getItemId().equals(Dialog.YES)) {
              presenter.onDeleteContact(gridMain.getSelectionModel().getSelectedItems());
            }
            else
              btnDel.enable();
          }
        });
  }

  private SelectionListener<ButtonEvent> createEditSelectionListener() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onEditContact();
      }
    };
  }

  private void onEditContact() {
    btnEdit.disable();
    presenter.onEditContact(gridMain.getSelectionModel().getSelectedItem());
  }

  private SelectionListener<ButtonEvent> createAddSelectionListener() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onAddContact();
      }
    };
  }

  private void onAddContact() {
    presenter.onAddContact();
  }

  private SelectionListener<ButtonEvent> createRefreshSelectionListener() {
    return new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent ce) {
        onRefresh();
      }
    };
  }

  private Grid<EventTypeDTO> createGrid() {
    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();


    columns.add(new ColumnConfig(EventTypeDTO.NAME, localization.eventTypeName(), 30));
    ColumnConfig column = new ColumnConfig(EventTypeDTO.IS_SINGLE, localization.isSingle(), 50);
    column.setRenderer(new GridCellRenderer<EventTypeDTO>() {
      @Override
      public Object render(EventTypeDTO model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<EventTypeDTO> store, Grid<EventTypeDTO> grid) {
        Image image;
        if ((Boolean) model.get(property)) {
          image = new Image(Resources.ICONS.buttonOk());
          image.setAltText(localization.isSingleYes());
          image.setTitle(localization.isSingleYes());
        }
        else {
          image = new Image(Resources.ICONS.buttonCancel());
          image.setAltText(localization.isSingleNo());
          image.setTitle(localization.isSingleNo());
        }

        return image == null ? "" : image;
      }
    });
    columns.add(column);

    ColumnModel cm = new ColumnModel(columns);
    ListStore store = presenter.loadContactList();

    LiveGridView liveView;
    liveView = new LiveGridView();
    liveView.setEmptyText(localization.listEmpty());

    Grid<EventTypeDTO> grid;
    grid = new Grid<EventTypeDTO>(store, cm);
    grid.setBorders(false);
    grid.setLoadMask(true);
    grid.setStripeRows(true);
    grid.setView(liveView);
    grid.getView().setAutoFill(true);
    grid.getView().setForceFit(true);
    grid.getView().setShowDirtyCells(false);
    grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<EventTypeDTO>() {
      @Override
      public void selectionChanged(SelectionChangedEvent<EventTypeDTO> se) {
        int selSize = se.getSelection().size();
        boolean itemSelected = selSize > 0;
        boolean singleItemSelected = selSize == 1;
        enableButtons(itemSelected, singleItemSelected, se.getSelection());
      }
    });
    grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<EventTypeDTO>>() {
      @Override
      public void handleEvent(GridEvent<EventTypeDTO> be) {
        onGridDoubleClick(be);
      }
    });
    return grid;
  }

  private void onGridDoubleClick(GridEvent<EventTypeDTO> be) {
    onEditContact();
  }

  private void enableButtons(boolean itemSelected, boolean singleItemSelected, List<EventTypeDTO> selection) {
    btnEdit.setEnabled(singleItemSelected);
    btnDel.setEnabled(itemSelected);
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

  private ToolBar createToolBarBottom(Grid<EventTypeDTO> grid) {
    ToolBar toolBarBottom;
    toolBarBottom = new ToolBar();
    LiveToolItem item;
    item = new LiveToolItem();
    item.bindGrid(grid);
    toolBarBottom.add(new FillToolItem());
    toolBarBottom.add(item);
    return toolBarBottom;
  }

  @Override
  public void onRefresh() {
    gridMain.getStore().getLoader().load();
  }
}
