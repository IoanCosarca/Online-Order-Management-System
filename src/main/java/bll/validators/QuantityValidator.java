package bll.validators;

import dataAccessLayer.dao.ProductDAO;
import model.Orders;
import model.Product;
/** Validator for Order quantity
 * @author Cosarca Ioan-Cristian*/
public class QuantityValidator implements Validator<Orders> {
    /** Checks and validates the inserted Order quantity to be smaller than the available stock from that product. */
    public void validate(Orders order)
    {
        ProductDAO p = new ProductDAO();
        Product product = p.findByName(order.getProductName());
        if (order.getQuantity() > product.getStock() && order.getQuantity() > 0) {
            throw new IllegalArgumentException("The order is too large!");
        }
    }
}
