package dataAccessLayer.dao;

import dataAccessLayer.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generic class for defining all the methods for accessing the database.
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;
	/** Initializes the class and obtains the class of generic type T. */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/** Creates the SQL query to select all the entries in the table specified by type with the given id. */
	private String createSelectQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE id=?");
		return sb.toString();
	}
	/** Gets the connection, calls a generic query to get all the entries in the generic type database and returns them as list. */
	public List<T> findAll()
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		List <T> list = null;
		try
		{
			statement = dbConnection.prepareStatement(createSelectAllQuery());
			rs = statement.executeQuery();
			list = createObjects(rs);
			return list;
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING,"DAO:findAll " + e.getMessage());
		}
		finally
		{
			ConnectionFactory.close(statement);
			ConnectionFactory.close(dbConnection);
			ConnectionFactory.close(rs);
		}
		return list;
	}
	/** Gets the connection, calls a generic query to get the object from the generic type database with the given id and returns it. */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	/** Given a ResultSet, obtains the list of model objects of type T. */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T) ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
	/** Gets the connection, calls a generic query to get the object from the generic type database with the given name and returns it. */
	public T findByName(String name)
	{
		Connection connection = null;
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		String query = createSelectNameQuery();
		try {
			connection = ConnectionFactory.getConnection();
			findStatement = connection.prepareStatement(query);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();

			return createObjects(rs).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	/** Creates the SQL query to select all the entries in the table specified by type with the given name. */
	private String createSelectNameQuery()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE name=?");
		return sb.toString();
	}
	/** Gets the connection and calls a generic query to insert the received object of type T into the generic type database. */
	public int insert(T t)
	{
		Connection connection = null;
		PreparedStatement insertStatement = null;
		ResultSet rs = null;
		int insertedId = -1;
		try
		{
			connection = ConnectionFactory.getConnection();
			insertStatement = connection.prepareStatement(createInsertQuery(), Statement.RETURN_GENERATED_KEYS);
			insertStatement = setInsertArguments(insertStatement, t);
			insertStatement.executeUpdate();

			rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, "DAO:insert " + e.getMessage());
		}
		finally
		{
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(connection);
			ConnectionFactory.close(rs);
		}
		return insertedId;
	}
	/** Initializes the insert object query with the arguments. */
	public PreparedStatement setInsertArguments(PreparedStatement insertStatement, T t) throws SQLException {
		return null;
	}
	/** Generic SQL query to insert an object in the database. */
	public String createInsertQuery() {
		return null;
	}
	/** Gets the connection and calls a generic query to update the generic type database with the object of type T. */
	public void update(T t)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		try
		{
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(createUpdateQuery());
			statement = setUpdateArguments(statement, t);
			statement.executeUpdate();
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING,"DAO:update " + e.getMessage());
		}
		finally
		{
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}
	/** Initializes the update database query with the arguments. */
	public PreparedStatement setUpdateArguments(PreparedStatement statement, T t) throws SQLException {
		return null;
	}
	/** Generic SQL query to update the database. */
	public String createUpdateQuery() {
		return null;
	}
	/** Gets the connection and calls a generic query to delete the entry with the given name from the generic type database. */
	public void delete(String name)
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try
		{
			deleteStatement = dbConnection.prepareStatement(createDeleteQuery());
			deleteStatement.setString(1, name);
			deleteStatement.executeUpdate();
		}
		catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		}
		finally
		{
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/** Generic SQL query to delete the entry from the database with the given name. */
	private String createDeleteQuery()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE name=?");
		return sb.toString();
	}
	/** Generic SQL query to select all the entries from the database. */
	private String createSelectAllQuery()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * ");
		sb.append("FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}
}
