package com.glensoft.data;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDataProvider {
	
	private Connection connection = null;
	
	public SqliteDataProvider()
	{
		
	}
	
	public void Connect()
	{
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Trinote.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Disconnect()
	{
		
	}
	
	public List<NoteBook> getNotebooks()
	{
		List<NoteBook> list = null;
		try (Statement statement = connection.createStatement()){
			try(ResultSet rs = statement.executeQuery("select id, name, comments from notebook order by name")){
				list = EntityMapper.MapNoteBook(rs);				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<NoteHeader> getNoteheaders(Long notebookid)
	{
		List<NoteHeader> list = null;
		try (PreparedStatement statement = connection.prepareStatement("select id, notebookid, name, comments from noteheader where notebookid = ? order by name")){
			statement.setLong(1, notebookid);
			try(ResultSet rs = statement.executeQuery()){
				list = EntityMapper.MapNoteHeader(rs);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<NoteDetail> getNotedetails(Long noteheaderid)
	{
		List<NoteDetail> list = null;
		try (PreparedStatement statement = connection.prepareStatement("select id, noteheaderid, name, comments, body, sourcecode from notedetail where noteheaderid = ? order by name")){
			statement.setLong(1, noteheaderid);
			try(ResultSet rs = statement.executeQuery()){
				list = EntityMapper.MapNoteDetail(rs);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public void putNoteDetail(NoteDetail noteDetail)
	{
		
	}
	
	public void postNoteDetail(NoteDetail noteDetail)
	{
		try(PreparedStatement statement = connection.prepareStatement("update notedetail set sourcecode = ?, comments = ?, body = ? where id = ?"))
		{
			statement.setString(1, noteDetail.getSourceCode());
			statement.setString(2, noteDetail.getComments());
			statement.setString(3, noteDetail.getBody());
			statement.setLong(4, noteDetail.getId());
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	

}
