package com.glensoft.ui;

import com.glensoft.data.*;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.wb.swt.SWTResourceManager;

public class NoteBodyViewer extends TitleAreaDialog {

	private NoteDetail noteDetail;
	private Boolean isDirty;
	
	private StyledText txtComments;
	private StyledText txtBody;
	private StyledText txtSourceCode;
	
	
	public NoteDetail getNoteDetail() {
		return noteDetail;
	}

	public void setNoteDetail(NoteDetail noteDetail) {
		this.noteDetail = noteDetail;
	}
	
	

	public Boolean getIsDirty() {
		return isDirty;
	}

	public void setIsDirty(Boolean isDirty) {
		this.isDirty = isDirty;
	}

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	
	public NoteBodyViewer(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.BORDER | SWT.MAX | SWT.RESIZE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Note Detail");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		SashForm sashForm = new SashForm(container, SWT.VERTICAL);
		
		txtComments = new StyledText(sashForm, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		
		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);
		
		txtBody = new StyledText(sashForm_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		
		txtSourceCode = new StyledText(sashForm_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		txtSourceCode.setFont(SWTResourceManager.getFont("Monaco", 10, SWT.NORMAL));
		sashForm_1.setWeights(new int[] {1, 1});
		sashForm.setWeights(new int[] {1, 3});

		this.txtComments.setText(this.noteDetail.getComments());
		this.txtBody.setText(this.noteDetail.getBody());
		this.txtSourceCode.setText(this.noteDetail.getSourceCode());
		
		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		
		int height = this.getShell().getDisplay().getPrimaryMonitor().getClientArea().height;
		int width = this.getShell().getDisplay().getPrimaryMonitor().getClientArea().width;
		return new Point(width, height);
		
		//return new Point(640, 480);
	}
	

	@Override
	protected void okPressed() {
		this.isDirty = false;
		this.isDirty = noteDetail.getSourceCode() != this.txtSourceCode.getText();
		this.isDirty = noteDetail.getComments() != this.txtComments.getText();
		this.isDirty = noteDetail.getBody() != this.txtBody.getText();
		if (this.isDirty)
		{
			noteDetail.setBody(this.txtBody.getText());
			noteDetail.setSourceCode(this.txtSourceCode.getText());
			noteDetail.setComments(this.txtComments.getText());
		}
		super.okPressed();
	}

}
