package bll.businessLayer;

import bll.validators.PriceValidator;
import bll.validators.StockValidator;
import bll.validators.Validator;
import dataAccessLayer.dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/** Product class logic. Defines the validators and describes what operations on the database will be performed for the Product table.
 * @author Cosarca Ioan-Cristian*/
public class ProductBLL {
    private final List<Validator<Product>> validators;
    private final ProductDAO productDAO;
    /** Initializes the validators and creates the link with the database (queries, connection). */
    public ProductBLL()
    {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new PriceValidator());
        validators.add(new StockValidator());

        productDAO = new ProductDAO();
    }
    /** Receives and adds a product to the database, if it is valid. */
    public void addProduct(Product product)
    {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        int id = productDAO.insert(product);
        if (id != -1) {
            product.setId(id);
        }
    }
    /** Receives a product object and tries to update its data in the database, if the product exists. */
    public void updateProduct(Product product)
    {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        if (productDAO.findById(product.getId()) == null) {
            throw new IllegalArgumentException("The product with the id " + product.getId() + " doesn't exist");
        }
        productDAO.update(product);
    }
    /** Receives the name of a product and deletes it if it's in the database. Returns 1 if success. */
    public int deleteProduct(String name)
    {
        productDAO.delete(name);
        return 1;
    }
    /** Searches the database and returns the product with the given name, if it exists. */
    public Product findProductByName(String name)
    {
        Product p = productDAO.findByName(name);
        if (p == null) {
            throw new NoSuchElementException("The product with the name " + name + " was not found!");
        }
        return p;
    }
    /** If the product with the given name was found, puts it in a list and returns it. */
    public List<Product> findName(String name)
    {
        Product p = findProductByName(name);
        List<Product> product = new ArrayList<>();
        product.add(p);
        return product;
    }
    /** Searches the database and returns the product with the given id, if it exists. */
    public Product findProductById(int id) {
        Product p = productDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return p;
    }
    /** If the product with the given id was found, puts it in a list and returns it. */
    public List<Product> findID(int id)
    {
        Product p = findProductById(id);
        List<Product> product = new ArrayList<>();
        product.add(p);
        return product;
    }
    /** Returns all the products currently present in the database. */
    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }
    /** Receives a list of all the product objects from the database and constructs a matrix with their field data. */
    public Object[][] getData(List<Product> list)
    {
        Object[][] data = new Object[list.size()][Product.class.getDeclaredFields().length];
        for (Product record : list)
        {
            Object[] o = new Object[]{record.getId(), record.getName(), record.getPrice(), record.getStock()};
            data[list.indexOf(record)] = o;
        }
        return data;
    }
}
