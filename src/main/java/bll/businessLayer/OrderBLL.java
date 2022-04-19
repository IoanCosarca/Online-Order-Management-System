package bll.businessLayer;

import bll.validators.QuantityValidator;
import bll.validators.Validator;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import dataAccessLayer.dao.OrderDAO;
import dataAccessLayer.dao.ProductDAO;
import model.Orders;
import model.Product;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/** Orders class logic. Defines the validators and describes what operations on the database will be performed for the Orders table.
 * @author Cosarca Ioan-Cristian*/
public class OrderBLL {
    private final List<Validator<Orders>> validators;
    private final OrderDAO orderDAO;
    /** Initializes the validator and creates the link with the database (queries, connection). */
    public OrderBLL()
    {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new QuantityValidator());

        orderDAO = new OrderDAO();
    }
    /** Receives and adds an order to the database, if it is valid. After that, it calls the bill method. */
    public void addOrder(Orders order) throws IllegalArgumentException
    {
        for (Validator<Orders> v : validators) {
            v.validate(order);
        }
        int id = orderDAO.insert(order);
        if (id != -1) {
            order.setId(id);
        }
        try {
            createBill(order);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /** Creates the bill for the received order as pdf. */
    private void createBill(Orders order) throws Exception
    {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("log"+order.getId()+".pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        Paragraph paragraph1 = new Paragraph("Order number " + order.getId(), font);
        document.add(paragraph1);
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        Paragraph paragraph2 = new Paragraph(LocalDate.now() + " " + LocalTime.now().format(myFormat), font);
        document.add(paragraph2);
        Paragraph paragraph3 = new Paragraph("Client: " + order.getClientName(), font);
        document.add(paragraph3);
        Paragraph paragraph4 = new Paragraph(order.getProductName(), font);
        document.add(paragraph4);
        ProductDAO pd = new ProductDAO();
        Product p = pd.findByName(order.getProductName());
        int price = p.getPrice();
        Paragraph paragraph5 = new Paragraph(order.getQuantity() + " buc. X " + price, font);
        document.add(paragraph5);
        Paragraph paragraph6 = new Paragraph("Total price: " + order.getQuantity() * price, font);
        document.add(paragraph6);

        document.close();
    }
    /** Searches the database and returns the order with the given id, if it exists. */
    public List<Orders> findOrderById(int id) {
        Orders o = orderDAO.findById(id);
        if (o == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        List<Orders> order = new ArrayList<>();
        order.add(o);
        return order;
    }
    /** Returns all the orders currently present in the database. */
    public List<Orders> findAllOrders()
    {
        return orderDAO.findAll();
    }
    /** Receives a list of all the order objects from the database and constructs a matrix with their field data. */
    public Object[][] getData(List<Orders> list)
    {
        Object[][] data = new Object[list.size()][Orders.class.getDeclaredFields().length];
        for (Orders record : list)
        {
            Object[] o = new Object[]{record.getId(), record.getClientName(), record.getProductName(), record.getQuantity()};
            data[list.indexOf(record)] = o;
        }
        return data;
    }
}
