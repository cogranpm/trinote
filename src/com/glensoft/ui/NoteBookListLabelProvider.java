package com.glensoft.ui;

import com.glensoft.data.*;
import org.eclipse.jface.viewers.LabelProvider;

public class NoteBookListLabelProvider extends LabelProvider {
	
	public String getText(Object element) {
		NoteBook nb = (NoteBook) element;
		return nb.getName();
		}

}
