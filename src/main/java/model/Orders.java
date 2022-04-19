package model;
/** Class that is mapped with the database table with the same name
 * @author Cosarca Ioan-Cristian*/
public class Orders {
    private int id;
    private String ClientName;
    private String ProductName;
    private int quantity;
    /** Constructor with parameters. */
    public Orders(String clientName, String productName, int quantity)
    {
        ClientName = clientName;
        ProductName = productName;
        this.quantity = quantity;
    }
    /** Constructor with no parameters for ResultSet object constructor. */
    public Orders() {
        super();
    }
    /** Returns the ID. */
    public int getId() {
        return id;
    }
    /** Sets the ID. */
    public void setId(int id) {
        this.id = id;
    }
    /** Returns the Client Name. */
    public String getClientName() {
        return ClientName;
    }
    /** Sets the Client Name. */
    public void setClientName(String clientName) {
        ClientName = clientName;
    }
    /** Returns the Product Name. */
    public String getProductName() {
        return ProductName;
    }
    /** Sets the Product Name. */
    public void setProductName(String productName) {
        ProductName = productName;
    }
    /** Returns the quantity. */
    public int getQuantity() {
        return quantity;
    }
    /** Sets the quantity. */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
