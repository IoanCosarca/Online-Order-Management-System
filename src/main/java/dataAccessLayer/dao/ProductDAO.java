package dataAccessLayer.dao;

import model.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/** Defines only methods and queries specific for the Product table
 * @author Cosarca Ioan-Cristian*/
public class ProductDAO extends AbstractDAO<Product> {
    /** Creates the insert query for the Product table. */
    public String createInsertQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO product (name,price,stock) ");
        sb.append("VALUES (?,?,?)");
        return sb.toString();
    }
    /** Initializes the insert query with the product data. */
    public PreparedStatement setInsertArguments(PreparedStatement insertStatement, Product t) throws SQLException
    {
        insertStatement.setString(1, t.getName());
        insertStatement.setInt(2, t.getPrice());
        insertStatement.setInt(3, t.getStock());
        return insertStatement;
    }
    /** Creates the update query for the Product table. */
    public String createUpdateQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE product ");
        sb.append("SET price=?, stock=? ");
        sb.append("WHERE name=?");
        return sb.toString();
    }
    /** Initializes the update query with the product data. */
    public PreparedStatement setUpdateArguments(PreparedStatement statement, Product t) throws SQLException
    {
        statement.setInt(1, t.getPrice());
        statement.setInt(2, t.getStock());
        statement.setString(3, t.getName());
        return statement;
    }
}
