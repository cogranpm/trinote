package com.glensoft.ui;

import org.eclipse.jface.viewers.LabelProvider;

import com.glensoft.data.NoteDetail;

public class NoteDetailListLabelProvider extends LabelProvider {
	public String getText(Object element) {
		NoteDetail nb = (NoteDetail) element;
		return nb.getName();
		}

}
