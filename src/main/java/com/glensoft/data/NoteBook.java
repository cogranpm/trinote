package com.glensoft.data;

public class NoteBook {
	private Long Id;
	private String Name;
	private String Comments;
	
	public NoteBook()
	{
		
	}
	
	public NoteBook(Long id, String name, String comments)
	{
		this.Id = id;
		this.Name = name;
		this.Comments = comments;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}

}
