package ru.n5g.birthdays.administrator.client.view;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.LiveGridView;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.LiveToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import ru.n5g.birthdays.administrator.client.localization.AdministratorLocalization;
import ru.n5g.birthdays.administrator.client.presenter.AdministratorPresenter;
import ru.n5g.birthdays.administrator.shared.bean.AdministratorListDTO;
import ru.n5g.birthdays.core.client.resources.Resources;
import ru.n5g.birthdays.core.client.util.IconUtils;
import ru.n5g.birthdays.core.shared.bean.UserRoleDTO;

public class AdministratorViewImpl<M extends AdministratorListDTO>  extends LayoutContainer implements AdministratorView<M> {
  private AdministratorPresenter presenter;
  private AdministratorLocalization localization;

  private Button btnAdd;
  private Button btnDel;
  private Button btnEdit;
  private Button btnRefresh;

  private ToolBar toolBarTop;
  private ToolBar toolBarBottom;
  private Grid<M> gridMain;

  public AdministratorViewImpl(AdministratorPresenter presenter, AdministratorLocalization localization){
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

    btnAdd = createBtnAdd();
    btnEdit = createBtnEdit();
    btnDel = createBtnDel();
    btnRefresh = createRefresh();
    toolBarTop = createToolBarTop();
    toolBarTop.add(btnAdd);
    toolBarTop.add(btnEdit);
    toolBarTop.add(btnDel);
    toolBarTop.add(new SeparatorToolItem());
    toolBarTop.add(btnRefresh);

    gridMain = createGrid();
    toolBarBottom = createToolBarBottom(gridMain);
    new QuickTip(gridMain);

    gridMain.addListener(Events.RowDoubleClick, new Listener<GridEvent<M>>() {
      @Override
      public void handleEvent(GridEvent<M> be) {
        if (btnEdit.isVisible() && btnEdit.isEnabled()) {
          presenter.editUser(be.getModel());
        }
      }
    });

    gridMain.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<M>() {
      @Override
      public void selectionChanged(SelectionChangedEvent<M> se) {
        if (se.getSelection().size() > 0) {
          btnEdit.enable();
          btnDel.enable();
        }
        else {
          btnEdit.disable();
          btnDel.disable();
        }
      }
    });

    cp.setTopComponent(toolBarTop);
    cp.add(gridMain);
    cp.setBottomComponent(toolBarBottom);
    add(cp);
  }

  @Override
  public void refresh() {
    gridMain.getStore().getLoader().load();
  }

  private Button createRefresh() {
    Button button = new Button();
    IconUtils.setLargeButton(button, Resources.ICONS.refresh32(), localization.btnRefresh());
    button.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent buttonEvent) {
        refreshUserList();
      }
    });
    return button;
  }

  private ToolBar createToolBarBottom(Grid<M> grid) {
    ToolBar toolBarBottom;
    toolBarBottom = new ToolBar();
    LiveToolItem item;
    item = new LiveToolItem();
    item.bindGrid(grid);
    toolBarBottom.add(new FillToolItem());
    toolBarBottom.add(item);
    return toolBarBottom;
  }

  private Button createBtnEdit() {
    Button button = new Button();
    IconUtils.setLargeButton(button, Resources.ICONS.edit32(), localization.btnEdit());
    button.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent buttonEvent) {
        presenter.editUser(gridMain.getSelectionModel().getSelectedItem());
      }
    });
    button.disable();
    return button;
  }

  private Button createBtnDel() {
    Button button = new Button();
    IconUtils.setLargeButton(button, Resources.ICONS.delete32(), localization.btnDel());
    button.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent buttonEvent) {
        MessageBox messageBox = MessageBox.confirm(localization.dialogConfirm(), localization.confirmationDeleting(),
            new Listener<MessageBoxEvent>() {
              @Override
              public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                if (btn.getItemId().equals("yes")) {
                  btnDel.disable();
                  presenter.delUser(gridMain.getSelectionModel().getSelectedItem());
                }
              }
            });
        messageBox.getDialog().getButtonById(Dialog.YES).setText(localization.yes());
        messageBox.getDialog().getButtonById(Dialog.NO).setText(localization.no());
      }
    });
    button.disable();
    return button;
  }

  private Button createBtnAdd() {
    Button button = new Button();
    IconUtils.setLargeButton(button, Resources.ICONS.add32(), localization.btnAdd());
    button.addSelectionListener(new SelectionListener<ButtonEvent>() {
      @Override
      public void componentSelected(ButtonEvent buttonEvent) {
        presenter.addUser();
      }
    });
    return button;
  }

  private ToolBar createToolBarTop() {
    ToolBar toolBar;
    toolBar = new ToolBar();
    toolBar.setBorders(false);
    return toolBar;
  }

  private Grid createGrid() {
    List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

    columns.add(new ColumnConfig(M.LOGIN, localization.userLogin(), 50));
    columns.add(new ColumnConfig((M.ROLE).concat(".").concat(UserRoleDTO.NAME), localization.userRole(), 50));
    columns.add(new ColumnConfig((M.COUNT_CONTACT), localization.countContact(), 50));
    columns.add(new ColumnConfig("2", localization.firstName(), 50));                //TODO показывать сколько контактов у пользователя
    columns.add(new ColumnConfig("3", localization.lastName(), 50));
    columns.add(new ColumnConfig("4", localization.smsLimit(), 50));                  //TODO показывать сколько смс сообщений он хочет отправить и сколько уже отправил

    ColumnModel cm = new ColumnModel(columns);
    ListStore store = presenter.loadUserList();

    LiveGridView liveView;
    liveView = new LiveGridView();
    liveView.setEmptyText(localization.listEmpty());

    Grid<M> grid;
    grid = new Grid<M>(store, cm);
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

  public void refreshUserList() {
    refresh();
  }

  public void setDeleteButtonEnabled(boolean b) {
    btnDel.setEnabled(b);
  }

}
