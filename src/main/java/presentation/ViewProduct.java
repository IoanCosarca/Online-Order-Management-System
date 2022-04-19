package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/** UI for the operations on the Product table
 * @author Cosarca Ioan-Cristian*/
public class ViewProduct extends JFrame {
    private JLabel ProductName;
    protected JTextField PName;
    private JLabel ProductPrice;
    protected JTextField Price;
    private JLabel ProductStock;
    protected JTextField Stock;
    private JLabel ProductID;
    protected JTextField PID;
    protected JButton ProductInsert;
    protected JButton ProductUpdate;
    protected JButton ProductDelete;
    protected JButton ProductFindName;
    protected JButton ProductFindID;
    protected JButton ProductFindAll;
    protected JButton GoBack;

    /** Sets the view parameters and adds the required buttons. */
    public ViewProduct()
    {
        getContentPane().setBounds(0,0,600,500);
        this.setSize(1200, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Product Operations");
        getContentPane().setLayout(null);

        Font FontBtn    = new Font("", Font.PLAIN,18);

        ProductName     = new JLabel("Name");
        PName           = new JTextField();
        ProductPrice    = new JLabel("Price");
        Price           = new JTextField();
        ProductStock    = new JLabel("Stock");
        Stock           = new JTextField();
        ProductID       = new JLabel("ID");
        PID             = new JTextField();
        ProductInsert   = new JButton("INSERT");
        ProductUpdate   = new JButton("UPDATE");
        ProductDelete   = new JButton("DELETE");
        ProductFindName = new JButton("FIND NAME");
        ProductFindID   = new JButton("FIND ID");
        ProductFindAll  = new JButton("VIEW ALL");
        GoBack          = new JButton("GO BACK");

        JPanel ProductData = new JPanel();
        ProductData.setLayout(new GridLayout(3,2));
        ProductData.setBounds(75,30,450,150);
        ProductData.add(ProductName);
        PName.setFont(FontBtn);
        ProductData.add(PName);
        ProductData.add(ProductPrice);
        Price.setFont(FontBtn);
        ProductData.add(Price);
        ProductData.add(ProductStock);
        Stock.setFont(FontBtn);
        ProductData.add(Stock);

        ProductID.setBounds(75,225,100,50);
        getContentPane().add(ProductID);
        PID.setFont(FontBtn);
        PID.setBounds(300,225,225,50);
        getContentPane().add(PID);

        getContentPane().add(ProductData);
        JPanel QueryProduct = new JPanel();
        QueryProduct.setLayout(new GridLayout(2,3));
        QueryProduct.setBounds(25,300,550,50);
        ProductInsert.setFont(FontBtn);
        QueryProduct.add(ProductInsert);
        ProductUpdate.setFont(FontBtn);
        QueryProduct.add(ProductUpdate);
        ProductDelete.setFont(FontBtn);
        QueryProduct.add(ProductDelete);
        ProductFindName.setFont(FontBtn);
        QueryProduct.add(ProductFindName);
        ProductFindID.setFont(FontBtn);
        QueryProduct.add(ProductFindID);
        ProductFindAll.setFont(FontBtn);
        QueryProduct.add(ProductFindAll);
        getContentPane().add(QueryProduct);

        GoBack.setBounds(225, 375, 150, 50);
        getContentPane().add(GoBack);

        this.setVisible(false);
    }
    /** Creates a JTable with the given parameters and shows it. */
    public void CreateTable(ArrayList<String> column, Object[][] data)
    {
        DefaultTableModel model = new DefaultTableModel(data, column.toArray());
        JTable table = new JTable(model);
        JScrollPane slider = new JScrollPane(table);

        slider.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        slider.setBounds(600,25,590,440);
        getContentPane().add(slider);
    }
}
