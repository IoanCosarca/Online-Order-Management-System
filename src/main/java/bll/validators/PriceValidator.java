package bll.validators;

import model.Product;
/** Validator for Product price
 * @author Cosarca Ioan-Cristian */
public class PriceValidator implements Validator<Product> {
    private static final int MIN_PRICE = 1;
    /** Checks and validates the inserted Product price to be bigger than 1. */
    public void validate(Product product) {
        if (product.getPrice() < MIN_PRICE) {
            throw new IllegalArgumentException("The product price is too little!");
        }
    }
}
