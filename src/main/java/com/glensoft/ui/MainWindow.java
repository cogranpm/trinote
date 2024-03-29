/*********************
 * how to run this when packaged
 * java -XstartOnFirstThread -jar TriNote.jar
 */
package com.glensoft.ui;

import com.glensoft.data.*;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class MainWindow extends ApplicationWindow {
	
	private SqliteDataProvider dp;
	
	private SashForm sashForm;
	private ListViewer lstNotebookViewer;
	private List lstNotebook;
	private ListViewer lstNoteHeaderViewer;
	private List lstNoteHeader;
	private ListViewer lstNoteBodyViewer;
	private List lstNoteBody;

	/**
	 * Create the application window.
	 */
	public MainWindow() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			sashForm = new SashForm(container, SWT.NONE);
			
			lstNotebookViewer = new ListViewer(sashForm, SWT.BORDER | SWT.V_SCROLL);
			lstNoteHeaderViewer = new ListViewer(sashForm, SWT.BORDER | SWT.V_SCROLL);
			lstNoteBodyViewer = new ListViewer(sashForm, SWT.BORDER | SWT.V_SCROLL);
			lstNotebook = lstNotebookViewer.getList();
			lstNoteHeader = lstNoteHeaderViewer.getList();
			lstNoteBody = lstNoteBodyViewer.getList();
			sashForm.setWeights(new int[] {1, 1, 1});
		}

		Startup();
		
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			MainWindow window = new MainWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("TriNote");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		int height = this.getShell().getDisplay().getPrimaryMonitor().getClientArea().height;
		int width = this.getShell().getDisplay().getPrimaryMonitor().getClientArea().width;
		return new Point(width, height);
		//return new Point(640, 480);
	}
	
	@Override
	protected void handleShellCloseEvent() {
		if (dp != null)
		{
			dp.Disconnect();
		}
		super.handleShellCloseEvent();
	}
	
	public void Startup()
	{
		this.dp = new SqliteDataProvider();
		dp.Connect();
		java.util.List<NoteBook> list = dp.getNotebooks();
		this.lstNotebookViewer.setLabelProvider(new NoteBookListLabelProvider());
		this.lstNotebookViewer.setContentProvider(new ArrayContentProvider());
		this.lstNotebookViewer.setInput(list);

		this.lstNotebookViewer.addSelectionChangedListener(
				new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event.getSelection();
						NoteBook nb = (NoteBook)selection.getFirstElement();
						java.util.List<NoteHeader> nhList = dp.getNoteheaders(nb.getId());
						lstNoteHeaderViewer.setLabelProvider(new NoteHeaderListLabelProvider());
						lstNoteHeaderViewer.setContentProvider(new ArrayContentProvider());
						lstNoteHeaderViewer.setInput(nhList);
					}
				});
		
		this.lstNoteHeaderViewer.addSelectionChangedListener(
				new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event.getSelection();
						NoteHeader nb = (NoteHeader)selection.getFirstElement();
						java.util.List<NoteDetail> nhList = dp.getNotedetails(nb.getId());
						lstNoteBodyViewer.setLabelProvider(new NoteDetailListLabelProvider());
						lstNoteBodyViewer.setContentProvider(new ArrayContentProvider());
						lstNoteBodyViewer.setInput(nhList);
					}
				});
		
		this.lstNoteBodyViewer.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event)
				{
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					NoteDetail nd = (NoteDetail)selection.getFirstElement();
					NoteBodyViewer dlg = new NoteBodyViewer(getParentShell());
					dlg.setNoteDetail(nd);
					dlg.create();
					if (dlg.open() == Window.OK)
					{
						//alter the stuff here
						if (dlg.getIsDirty())
						{
							dp.postNoteDetail(nd);
						}
					}
					
				}
				});
	}
}
