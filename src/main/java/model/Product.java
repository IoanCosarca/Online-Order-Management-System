package model;
/** Class that is mapped with the database table with the same name
 * @author Cosarca Ioan-Cristian*/
public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;
    /** Constructor with parameters. */
    public Product(String name, int price, int stock)
    {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    /** Constructor with no parameters for ResultSet object constructor. */
    public Product() {
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
    /** Returns the Product Name. */
    public String getName() {
        return name;
    }
    /** Sets the Product Name. */
    public void setName(String name) {
        this.name = name;
    }
    /** Returns the price. */
    public int getPrice() {
        return price;
    }
    /** Sets the price. */
    public void setPrice(int price) {
        this.price = price;
    }
    /** Returns the stock. */
    public int getStock() {
        return stock;
    }
    /** Sets the stock. */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
