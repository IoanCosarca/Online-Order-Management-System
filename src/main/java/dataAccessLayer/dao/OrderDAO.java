package dataAccessLayer.dao;

import model.Orders;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/** Defines only methods and queries specific for the Orders table
 * @author Cosarca Ioan-Cristian*/
public class OrderDAO extends AbstractDAO<Orders> {
    /** Creates the insert query for the Orders table. */
    public String createInsertQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO orders (ClientName,ProductName,quantity) ");
        sb.append("VALUES (?,?,?)");
        return sb.toString();
    }
    /** Initializes the insert query with the order data. */
    public PreparedStatement setInsertArguments(PreparedStatement insertStatement, Orders t) throws SQLException
    {
        insertStatement.setString(1, t.getClientName());
        insertStatement.setString(2, t.getProductName());
        insertStatement.setInt(3, t.getQuantity());
        return insertStatement;
    }
}
