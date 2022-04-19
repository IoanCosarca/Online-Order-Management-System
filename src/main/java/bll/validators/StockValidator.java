package bll.validators;

import model.Product;
/** Validator for Product stock
 * @author Cosarca Ioan-Cristian*/
public class StockValidator implements Validator<Product> {
    private static final int MIN_STOCK = 0;
    /** Checks and validates the inserted Product stock to be positive. */
    public void validate(Product product) {
        if (product.getStock() < MIN_STOCK) {
            throw new IllegalArgumentException("The stock of a product cannot be negative!!!");
        }
    }
}
