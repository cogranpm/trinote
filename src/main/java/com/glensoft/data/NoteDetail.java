package com.glensoft.data;

public class NoteDetail {
	private Long id;
	private Long noteHeaderId;
	private String name;
	private String comments;	
	private String body;
	private String sourceCode;
	
	
	public NoteDetail() {
		super();
		// TODO Auto-generated constructor stub
	}


	public NoteDetail(Long id, Long noteHeaderId, String name, String comments, String body, String sourceCode) {
		super();
		this.id = id;
		this.noteHeaderId = noteHeaderId;
		this.name = name;
		this.comments = comments;
		this.body = body;
		this.sourceCode = sourceCode;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getNoteHeaderId() {
		return noteHeaderId;
	}


	public void setNoteHeaderId(Long noteHeaderId) {
		this.noteHeaderId = noteHeaderId;
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


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getSourceCode() {
		return sourceCode;
	}


	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	
	
	

}
