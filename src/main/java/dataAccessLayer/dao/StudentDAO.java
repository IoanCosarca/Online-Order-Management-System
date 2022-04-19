package dataAccessLayer.dao;

import model.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**Defines only methods and queries specific for the Student table*/
public class StudentDAO extends AbstractDAO<Student> {
	// uses basic CRUD methods from superclass

	// TODO: create only student specific queries
	/** Creates the insert query for the Student table. */
	public String createInsertQuery()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO student (name,address,email,age) ");
		sb.append("VALUES (?,?,?,?)");
		return sb.toString();
	}
	/** Initializes the insert query with the student data. */
	public PreparedStatement setInsertArguments(PreparedStatement insertStatement, Student t) throws SQLException
	{
		insertStatement.setString(1, t.getName());
		insertStatement.setString(2, t.getAddress());
		insertStatement.setString(3, t.getEmail());
		insertStatement.setInt(4, t.getAge());
		return insertStatement;
	}
	/** Creates the update query for the Student table. */
	public String createUpdateQuery()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE student ");
		sb.append("SET address=?, email=?, age=? ");
		sb.append("WHERE name=?");
		return sb.toString();
	}
	/** Initializes the update query with the student data. */
	public PreparedStatement setUpdateArguments(PreparedStatement statement, Student t) throws SQLException
	{
		statement.setString(1, t.getAddress());
		statement.setString(2, t.getEmail());
		statement.setInt(3, t.getAge());
		statement.setString(4, t.getName());
		return statement;
	}

}
