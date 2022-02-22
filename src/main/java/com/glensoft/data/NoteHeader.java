package com.glensoft.data;

public class NoteHeader {
	
	private Long id;
	private Long noteBookId;
	private String name;
	private String comments;	
	
	public NoteHeader()
	{
		
	}
	
	public NoteHeader(Long id, Long notebookid, String name, String comments)
	{
		this.id = id;
		this.noteBookId = notebookid;
		this.name = name;
		this.comments= comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNoteBookId() {
		return noteBookId;
	}

	public void setNoteBookId(Long noteBookId) {
		this.noteBookId = noteBookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}
