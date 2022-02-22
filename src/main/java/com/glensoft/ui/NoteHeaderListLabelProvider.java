package com.glensoft.ui;

import org.eclipse.jface.viewers.LabelProvider;

import com.glensoft.data.NoteHeader;

public class NoteHeaderListLabelProvider extends LabelProvider {
	public String getText(Object element) {
		NoteHeader nb = (NoteHeader) element;
		return nb.getName();
		}

}
