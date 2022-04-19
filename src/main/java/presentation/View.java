package presentation;

import javax.swing.*;
import java.awt.*;
/** UI for the main menu
 * @author Cosarca Ioan-Cristian */
public class View extends JFrame {
    protected JButton ClientOperations;
    protected JButton ProductOperations;
    protected JButton CreateOrder;
    /** Sets the view parameters and adds the required buttons. */
    public View()
    {
        getContentPane().setBounds(0,0,500,500);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Order management application");
        getContentPane().setLayout(null);

        Font FontTitles     = new Font("", Font.PLAIN,20);

        ClientOperations    = new JButton("Client Operations");
        ProductOperations   = new JButton("Product Operations");
        CreateOrder         = new JButton("Create Order");

        ClientOperations.setFont(FontTitles);
        ClientOperations.setBounds(100,50,300,50);
        getContentPane().add(ClientOperations);
        ProductOperations.setFont(FontTitles);
        ProductOperations.setBounds(100,150,300,50);
        getContentPane().add(ProductOperations);
        CreateOrder.setFont(FontTitles);
        CreateOrder.setBounds(100,250,300,50);
        getContentPane().add(CreateOrder);

        this.setVisible(true);
    }
}
