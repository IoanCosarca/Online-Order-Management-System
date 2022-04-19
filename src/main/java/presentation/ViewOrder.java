package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/** UI for the operations on the Orders table
 * @author Cosarca Ioan-Cristian*/
public class ViewOrder extends JFrame {
    protected JComboBox<String> ListClients;
    protected JComboBox<String> ListProducts;
    private JLabel OrderQuantity;
    protected JTextField quantity;
    private JLabel ID;
    protected JTextField OID;
    protected JButton PlaceOrder;
    protected JButton ViewOrderByID;
    protected JButton ViewAllOrders;
    protected JTextArea OrderInfo;
    protected JButton GoBack;

    /** Sets the view parameters and adds the required buttons. */
    public ViewOrder(ArrayList<String> clients, ArrayList<String> products)
    {
        getContentPane().setBounds(0,0,600,500);
        this.setSize(1200, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Order Operations");
        getContentPane().setLayout(null);

        String[] AllClients     = new String[clients.size()];
        for (int i = 0; i < clients.size(); i++) {
            AllClients[i] = clients.get(i);
        }
        ListClients = new JComboBox<String>(AllClients);
        String[] AllProducts    = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            AllProducts[i] = products.get(i);
        }
        ListProducts    = new JComboBox<String>(AllProducts);
        OrderQuantity   = new JLabel("Quantity");
        quantity        = new JTextField();
        ID              = new JLabel("ID");
        OID             = new JTextField();
        PlaceOrder      = new JButton("Place Order");
        ViewOrderByID   = new JButton("View By ID");
        ViewAllOrders   = new JButton("View All Orders");
        OrderInfo       = new JTextArea();
        GoBack          = new JButton("GO BACK");

        Font FontBtn    = new Font("", Font.PLAIN,20);

        ListClients.setBounds(75,25,200,100);
        ListClients.setFont(FontBtn);
        getContentPane().add(ListClients);
        ListProducts.setBounds(325,25,200,100);
        ListProducts.setFont(FontBtn);
        getContentPane().add(ListProducts);

        OrderQuantity.setBounds(200,150,100,50);
        OrderQuantity.setFont(FontBtn);
        getContentPane().add(OrderQuantity);
        quantity.setBounds(325,150,200,50);
        quantity.setFont(FontBtn);
        getContentPane().add(quantity);
        ID.setBounds(250,210,100,50);
        ID.setFont(FontBtn);
        getContentPane().add(ID);
        OID.setBounds(325,210,200,50);
        OID.setFont(FontBtn);
        getContentPane().add(OID);

        JPanel QueryOrder = new JPanel();
        QueryOrder.setLayout(new BoxLayout(QueryOrder, BoxLayout.X_AXIS));
        QueryOrder.setBounds(75,280,550,25);
        PlaceOrder.setFont(FontBtn);
        QueryOrder.add(PlaceOrder);
        ViewOrderByID.setFont(FontBtn);
        QueryOrder.add(ViewOrderByID);
        ViewAllOrders.setFont(FontBtn);
        QueryOrder.add(ViewAllOrders);
        getContentPane().add(QueryOrder);
        OrderInfo.setBounds(25,325,525, 50);
        OrderInfo.setEditable(false);
        OrderInfo.setFont(new Font("", Font.PLAIN,20));
        getContentPane().add(OrderInfo);

        GoBack.setBounds(225, 400, 150, 50);
        GoBack.setFont(FontBtn);
        getContentPane().add(GoBack);

        this.setVisible(false);
    }
    /** Creates a JTable with the given parameters and shows it. */
    public void CreateTable(ArrayList<String> column, Object[][] data)
    {
        DefaultTableModel model = new DefaultTableModel(data, column.toArray());
        JTable table            = new JTable(model);
        JScrollPane slider      = new JScrollPane(table);

        slider.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        slider.setBounds(600,25,590,440);
        getContentPane().add(slider);
    }
}
