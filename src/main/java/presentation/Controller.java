package presentation;

import bll.businessLayer.OrderBLL;
import bll.businessLayer.ProductBLL;
import bll.businessLayer.StudentBLL;
import dataAccessLayer.dao.AbstractDAO;
import dataAccessLayer.dao.ProductDAO;
import model.Orders;
import model.Product;
import model.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/** Controller for specifying what events or methods will happen when the user interacts with the UI
 * @author Cosarca Ioan-Cristian*/
public class Controller implements ActionListener {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final StudentBLL studentBll;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;
    private final View view;
    private final ArrayList<String> Clients;
    private final ArrayList<String> Products;
    private ViewClient viewClient;
    private ViewProduct viewProduct;
    private ViewOrder viewOrder;

    private final ArrayList<String> columnClient;
    private final ArrayList<String> columnProduct;
    private final ArrayList<String> columnOrders;
    /** Initializes the controller and the button listeners for the main view */
    public Controller(StudentBLL studentBll, ProductBLL productBLL, OrderBLL orderBLL, View view) {
        this.studentBll = studentBll;
        this.productBLL = productBLL;
        this.orderBLL = orderBLL;
        this.view = view;

        List<Product> l1 = productBLL.findAllProducts();
        List<Student> l2 = studentBll.findAllStudents();
        columnClient = new ArrayList<>();
        for (Field f : Student.class.getDeclaredFields()) {
            columnClient.add(f.getName());
        }
        columnProduct = new ArrayList<>();
        for (Field f : Product.class.getDeclaredFields()) {
            columnProduct.add(f.getName());
        }
        columnOrders = new ArrayList<>();
        for (Field f : Orders.class.getDeclaredFields()) {
            columnOrders.add(f.getName());
        }

        Clients = new ArrayList<>();
        for (Student s : l2) {
            Clients.add(s.getName());
        }
        Products = new ArrayList<>();
        for (Product p : l1) {
            Products.add(p.getName());
        }

        viewClient = new ViewClient();
        viewProduct = new ViewProduct();
        viewOrder = new ViewOrder(Clients, Products);
        view.ClientOperations.addActionListener(this);
        view.ProductOperations.addActionListener(this);
        view.CreateOrder.addActionListener(this);
    }
    /** Describes what happens when each button from the application is pressed. Initializes the button listeners for table-specific views.  */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.ClientOperations) {
            view.setVisible(false);
            viewClient = new ViewClient();
            viewClient.CreateTable(columnClient, studentBll.getData(studentBll.findAllStudents()));
            viewClient.setVisible(true);
            viewClient.StudentInsert.addActionListener(this);
            viewClient.StudentUpdate.addActionListener(this);
            viewClient.StudentDelete.addActionListener(this);
            viewClient.StudentFindName.addActionListener(this);
            viewClient.StudentFindID.addActionListener(this);
            viewClient.StudentFindAll.addActionListener(this);
            viewClient.GoBack.addActionListener(this);
        }

        if (e.getSource() == view.ProductOperations) {
            view.setVisible(false);
            viewProduct = new ViewProduct();
            viewProduct.CreateTable(columnProduct, productBLL.getData(productBLL.findAllProducts()));
            viewProduct.setVisible(true);
            viewProduct.ProductInsert.addActionListener(this);
            viewProduct.ProductUpdate.addActionListener(this);
            viewProduct.ProductDelete.addActionListener(this);
            viewProduct.ProductFindName.addActionListener(this);
            viewProduct.ProductFindID.addActionListener(this);
            viewProduct.ProductFindAll.addActionListener(this);
            viewProduct.GoBack.addActionListener(this);
        }

        if (e.getSource() == view.CreateOrder) {
            view.setVisible(false);
            viewOrder = new ViewOrder(Clients, Products);
            viewOrder.CreateTable(columnOrders, orderBLL.getData(orderBLL.findAllOrders()));
            viewOrder.setVisible(true);
            viewOrder.PlaceOrder.addActionListener(this);
            viewOrder.ViewOrderByID.addActionListener(this);
            viewOrder.ViewAllOrders.addActionListener(this);
            viewOrder.GoBack.addActionListener(this);
        }

        if (e.getSource() == viewClient.StudentInsert) {
            String name = viewClient.SName.getText();
            String address = viewClient.Address.getText();
            String email = viewClient.Email.getText();
            int age = Integer.parseInt(viewClient.Age.getText());
            Student student = new Student(name, address, email, age);
            try {
                studentBll.addStudent(student);
                Clients.add(name);
                viewClient.CreateTable(columnClient, studentBll.getData(studentBll.findAllStudents()));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Insert Student " + ex.getMessage());
            }
        }

        if (e.getSource() == viewClient.StudentUpdate) {
            String name = viewClient.SName.getText();
            try {
                Student st = studentBll.findStudentByName(name);
                st.setAddress(viewClient.Address.getText());
                st.setEmail(viewClient.Email.getText());
                st.setAge(Integer.parseInt(viewClient.Age.getText()));
                studentBll.updateStudent(st);
                viewClient.CreateTable(columnClient, studentBll.getData(studentBll.findAllStudents()));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Update Student " + ex.getMessage());
            }
        }

        if (e.getSource() == viewClient.StudentDelete) {
            String name = viewClient.SName.getText();
            try {
                if (studentBll.deleteStudent(name) == 1) {
                    Clients.remove(name);
                }
                viewClient.CreateTable(columnClient, studentBll.getData(studentBll.findAllStudents()));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Delete Student " + ex.getMessage());
            }
        }

        if (e.getSource() == viewClient.StudentFindName) {
            String name = viewClient.SName.getText();
            try {
                viewClient.CreateTable(columnClient, studentBll.getData(studentBll.findName(name)));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Find Student by ID " + ex.getMessage());
            }
        }

        if (e.getSource() == viewClient.StudentFindID) {
            int id = Integer.parseInt(viewClient.SID.getText());
            try {
                viewClient.CreateTable(columnClient, studentBll.getData(studentBll.findID(id)));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Find Student by ID " + ex.getMessage());
            }
        }

        if (e.getSource() == viewClient.StudentFindAll) {
            viewClient.CreateTable(columnClient, studentBll.getData(studentBll.findAllStudents()));
        }

        if (e.getSource() == viewClient.GoBack) {
            viewClient.setVisible(false);
            view.setVisible(true);
        }

        if (e.getSource() == viewProduct.ProductInsert) {
            String name = viewProduct.PName.getText();
            int price = Integer.parseInt(viewProduct.Price.getText());
            int stock = Integer.parseInt(viewProduct.Stock.getText());
            Product product = new Product(name, price, stock);
            try {
                productBLL.addProduct(product);
                Products.add(name);
                viewProduct.CreateTable(columnProduct, productBLL.getData(productBLL.findAllProducts()));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Insert Product " + ex.getMessage());
            }
        }

        if (e.getSource() == viewProduct.ProductUpdate) {
            String name = viewProduct.PName.getText();
            try {
                Product p = productBLL.findProductByName(name);
                p.setPrice(Integer.parseInt(viewProduct.Price.getText()));
                p.setStock(Integer.parseInt(viewProduct.Stock.getText()));
                productBLL.updateProduct(p);
                viewProduct.CreateTable(columnProduct, productBLL.getData(productBLL.findAllProducts()));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Update Product " + ex.getMessage());
            }
        }

        if (e.getSource() == viewProduct.ProductDelete) {
            String name = viewProduct.PName.getText();
            try {
                if (productBLL.deleteProduct(name) == 1) {
                    Products.remove(name);
                }
                viewProduct.CreateTable(columnProduct, productBLL.getData(productBLL.findAllProducts()));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Delete Product " + ex.getMessage());
            }
        }

        if (e.getSource() == viewProduct.ProductFindName) {
            String name = viewProduct.PName.getText();
            try {
                viewProduct.CreateTable(columnProduct, productBLL.getData(productBLL.findName(name)));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Find Product by ID " + ex.getMessage());
            }
        }

        if (e.getSource() == viewProduct.ProductFindID) {
            int id = Integer.parseInt(viewProduct.PID.getText());
            try {
                viewProduct.CreateTable(columnProduct, productBLL.getData(productBLL.findID(id)));
            } catch (Exception ex) {
                LOGGER.log(Level.WARNING, "Find Product by ID " + ex.getMessage());
            }
        }

        if (e.getSource() == viewProduct.ProductFindAll) {
            viewProduct.CreateTable(columnProduct, productBLL.getData(productBLL.findAllProducts()));
        }

        if (e.getSource() == viewProduct.GoBack) {
            viewProduct.setVisible(false);
            view.setVisible(true);
        }

        if (e.getSource() == viewOrder.PlaceOrder) {
            String ClientName = viewOrder.ListClients.getSelectedItem().toString();
            String ProductName = viewOrder.ListProducts.getSelectedItem().toString();
            int quantity = Integer.parseInt(viewOrder.quantity.getText());

            Orders order = new Orders(ClientName, ProductName, quantity);
            try {
                orderBLL.addOrder(order);
                ProductDAO prod = new ProductDAO();
                Product p = prod.findByName(ProductName);
                int newStock = p.getStock() - quantity;
                String PName = p.getName();
                if (newStock == 0) {
                    productBLL.deleteProduct(PName);
                } else {
                    p.setStock(newStock);
                    productBLL.updateProduct(p);
                }
                viewOrder.CreateTable(columnOrders, orderBLL.getData(orderBLL.findAllOrders()));
            } catch (Exception ex) {
                viewOrder.OrderInfo.setText(ex.getMessage());
            }
        }

        if (e.getSource() == viewOrder.ViewOrderByID) {
            int id = Integer.parseInt(viewOrder.OID.getText());
            try {
                viewOrder.CreateTable(columnOrders, orderBLL.getData(orderBLL.findOrderById(id)));
            } catch (Exception ex) {
                viewOrder.OrderInfo.setText(ex.getMessage());
            }
        }

        if (e.getSource() == viewOrder.ViewAllOrders) {
            viewOrder.CreateTable(columnOrders, orderBLL.getData(orderBLL.findAllOrders()));
        }

        if (e.getSource() == viewOrder.GoBack) {
            viewOrder.setVisible(false);
            view.setVisible(true);
        }
    }
}
