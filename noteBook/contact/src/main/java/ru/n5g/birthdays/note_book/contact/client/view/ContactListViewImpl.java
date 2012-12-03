package ru.n5g.birthdays.note_book.contact.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
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
import ru.n5g.birthdays.components.client.view.SimpleCreateField;
import ru.n5g.birthdays.core.client.dialog.MyMessageBox;
import ru.n5g.birthdays.note_book.contact.client.localization.ContactListLocalization;
import ru.n5g.birthdays.note_book.contact.client.presenter.ContactListPresenter;
import ru.n5g.birthdays.note_book.contact.shared.bean.ContactListDTO;

public class ContactListViewImpl extends LayoutContainer implements ContactListPresenter.ContactView {

  private ContactListPresenter presenter;
  private ContactListLocalization localization;

  private Grid<ContactListDTO> gridMain;
  private ToolBar toolBarTop;
  private ToolBar toolBarBottom;

  private Button btnRefresh;
  private Button btnAdd;
  private Button btnEdit;
  private Button btnDel;

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

    btnAdd = SimpleCreateField.createButtonWithIcon("btn-contact-new", localization.btnAdd()
        , "btn_20120925140802", createAddSelectionListener());
    btnEdit = SimpleCreateField.createButtonWithIcon("btn-contact-edit", localization.btnEdit()
        , "btn_20120925140803", createEditSelectionListener());
    btnDel = SimpleCreateField.createButtonWithIcon("btn-contact-delete", localization.btnDelete()
        , "btn_20120925140804", createDeleteSelectionListener());
    btnRefresh = SimpleCreateField.createButtonWithIcon("btn-refresh-list", localization.btnRefresh()
        , "btn_20120925140401", createRefreshSelectionListener());
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

  private Grid<ContactListDTO> createGrid() {
    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

    columns.add(new ColumnConfig(ContactListDTO.NICKNAME, localization.nickname(), 50));
    columns.add(new ColumnConfig((ContactListDTO.FIO), localization.fio(), 70));
    columns.add(new ColumnConfig((ContactListDTO.EVENT_LIST), localization.eventList(), 250));
    columns.add(new ColumnConfig((ContactListDTO.COMMENT), localization.comment(), 200));


    ColumnModel cm = new ColumnModel(columns);
    ListStore store = presenter.loadContactList();

    LiveGridView liveView;
    liveView = new LiveGridView();
    liveView.setEmptyText(localization.listEmpty());

    Grid<ContactListDTO> grid;
    grid = new Grid<ContactListDTO>(store, cm);
    grid.setBorders(false);
    grid.setLoadMask(true);
    grid.setStripeRows(true);
    grid.setView(liveView);
    grid.getView().setAutoFill(true);
    grid.getView().setForceFit(true);
    grid.getView().setShowDirtyCells(false);
    grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ContactListDTO>() {
      @Override
      public void selectionChanged(SelectionChangedEvent<ContactListDTO> se) {
        int selSize = se.getSelection().size();
        boolean itemSelected = selSize > 0;
        boolean singleItemSelected = selSize == 1;
        enableButtons(itemSelected, singleItemSelected, se.getSelection());
      }
    });
    grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<ContactListDTO>>() {
      @Override
      public void handleEvent(GridEvent<ContactListDTO> be) {
        onGridDoubleClick(be);
      }
    });
    return grid;
  }

  private void onGridDoubleClick(GridEvent<ContactListDTO> be) {
    onEditContact();
  }

  private void enableButtons(boolean itemSelected, boolean singleItemSelected, List<ContactListDTO> selection) {
    btnEdit.setEnabled(singleItemSelected);
    btnDel.setEnabled(itemSelected);
  }


  private ToolBar createToolBarBottom(Grid<ContactListDTO> grid) {
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
