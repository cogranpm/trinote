package com.glensoft.data;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EntityMapper {
	
	public static List<NoteBook> MapNoteBook(ResultSet rs)
	{
		List<NoteBook> list = new ArrayList<NoteBook>();
		try {
			while(rs.next())
			{
				NoteBook nb = new NoteBook();
				nb.setComments(rs.getString("comments"));
				nb.setId(rs.getLong("id"));
				nb.setName(rs.getString("name"));
				list.add(nb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static List<NoteHeader> MapNoteHeader(ResultSet rs)
	{
		List<NoteHeader> list = new ArrayList<NoteHeader>();
		try {
			while(rs.next())
			{
				NoteHeader nb = new NoteHeader();
				nb.setComments(rs.getString("comments"));
				nb.setId(rs.getLong("id"));
				nb.setName(rs.getString("name"));
				nb.setNoteBookId(rs.getLong("notebookid"));
				list.add(nb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static List<NoteDetail> MapNoteDetail(ResultSet rs)
	{
		List<NoteDetail> list = new ArrayList<NoteDetail>();
		try {
			while(rs.next())
			{
				NoteDetail nb = new NoteDetail();
				nb.setComments(rs.getString("comments"));
				if (rs.wasNull())
				{
					nb.setComments("");
				}
				nb.setId(rs.getLong("id"));
				nb.setName(rs.getString("name"));
				nb.setNoteHeaderId(rs.getLong("noteheaderid"));
				nb.setBody(rs.getString("body"));
				if (rs.wasNull())
				{
					nb.setBody("");
				}
				nb.setSourceCode(rs.getString("sourcecode"));
				if (rs.wasNull())
				{
					nb.setSourceCode("");
				}
				list.add(nb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	

}
 